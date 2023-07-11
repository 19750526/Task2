package com.example.demo.service;

import com.example.demo.domain.Apartment;
import com.example.demo.domain.Reservation;
import com.example.demo.domain.Tenant;
import com.example.demo.dto.BaseReservationDto;
import com.example.demo.dto.ChangeReservationDto;
import com.example.demo.dto.CreateReservationDto;
import com.example.demo.dto.ReservationDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UnableToRegisterReservation;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.repository.ApartmentRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.demo.util.PeriodUtils.arePeriodsOverlapping;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final ApartmentRepository apartmentRepository;
    private final TenantRepository tenantRepository;


    public List<ReservationDto> allReservations() {
        return reservationRepository.findAll().stream().map(reservationMapper::mapToDto).toList();
    }

    public ReservationDto makeReservation(CreateReservationDto dto) {

        List<Reservation> existingReservations = reservationRepository.findAllByApartamentId(dto.getApartmentId());

        if (existingReservations.isEmpty()) {
            return createReservation(dto);
        }

        var hasOverlappingDates = hasOverlappingDates(dto, existingReservations);

        if (hasOverlappingDates) {
            throw new UnableToRegisterReservation("Apartment with given id is already reserved during given period");
        }

        return createReservation(dto);
    }

    private boolean hasOverlappingDates(BaseReservationDto dto, List<Reservation> existingReservations) {
        LocalDate newStart = dto.getStartDate();
        LocalDate newStop = dto.getStopDate();
//        (StartA <= EndB) and (EndA >= StartB)
        return existingReservations.stream().anyMatch(existing ->
                arePeriodsOverlapping(newStart, newStop,
                        existing.getStartDate(), existing.getStopDate()));
    }

    private ReservationDto createReservation(CreateReservationDto dto) {
        Apartment apartment = apartmentRepository.findById(dto.getApartmentId())
                .orElseThrow(() -> new NotFoundException("Apartment with given id not found."));

        Tenant tenant = tenantRepository.findById(dto.getTenantId())
                .orElseThrow(() -> new NotFoundException("Tenant with given id not found."));

        var reservation = Reservation.builder()
                .apartment(apartment)
                .tenant(tenant)
                .startDate(dto.getStartDate())
                .stopDate(dto.getStopDate())
                .build();
        reservation.countPrice();

        reservation = reservationRepository.save(reservation);

        return reservationMapper.mapToDto(reservation);
    }


    public ReservationDto changeReservation(Long id, ChangeReservationDto dto) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation with given id not found."));

        Long apartmentId = reservation.getApartment().getId();
        List<Reservation> existingReservations = reservationRepository.findAllByApartamentId(apartmentId);
        var hasOverlappingDates = hasOverlappingDates(dto, existingReservations);

        if (hasOverlappingDates) {
            throw new UnableToRegisterReservation("Reservation with given id can not be changed due to given period.");
        }

        reservation.setStartDate(dto.getStartDate());
        reservation.setStopDate(dto.getStopDate());
        reservation.countPrice();
        return reservationMapper.mapToDto(reservationRepository.save(reservation));
    }
}
