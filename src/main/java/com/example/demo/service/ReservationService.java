package com.example.demo.service;

import com.example.demo.dto.ChangeReservationDto;
import com.example.demo.dto.CreateReservationDto;
import com.example.demo.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    List<ReservationDto> allReservations();

    ReservationDto makeReservation(CreateReservationDto dto);

    ReservationDto changeReservation(Long id, ChangeReservationDto dto);

    List<ReservationDto> findByTenantName(String tenantName);

    List<ReservationDto> findByApartmentId(Long apartmentId);

}
