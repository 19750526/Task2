package com.example.demo.service;

import com.example.demo.dto.ReservationDto;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public List<ReservationDto> allReservations() {
        return reservationRepository.findAll().stream().map(reservationMapper::mapToDto).toList();
    }

}
