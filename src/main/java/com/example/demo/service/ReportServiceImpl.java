package com.example.demo.service;

import com.example.demo.domain.Reservation;
import com.example.demo.dto.PeriodicReservationReportDto;
import com.example.demo.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReservationRepository reservationRepository;

    @Override
    public PeriodicReservationReportDto periodicReport(LocalDate startDate, LocalDate stopDate, Long apartmentId) {
        List<Reservation> reservations = reservationRepository.findByStartDateGreaterThanEqualAndStopDateLessThanEqualAndAndApartment_Id(startDate, stopDate, apartmentId);
        int bookedDays = 0;
        int reservationCount = reservations.size();

        for (Reservation reservation : reservations) {
            int daysBetween = (int) ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getStopDate()) + 1;
            bookedDays += daysBetween;
        }

        return PeriodicReservationReportDto.builder()
                .reservationCount(reservationCount)
                .bookedDaysCount(bookedDays)
                .startDate(startDate)
                .stopDate(stopDate)
                .build();
    }
}
