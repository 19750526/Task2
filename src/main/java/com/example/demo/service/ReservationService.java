package com.example.demo.service;

import com.example.demo.dto.ChangeReservationDto;
import com.example.demo.dto.CreateReservationDto;
import com.example.demo.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    public List<ReservationDto> allReservations();
    public ReservationDto makeReservation(CreateReservationDto dto);
    public ReservationDto changeReservation(Long id, ChangeReservationDto dto);
    public List<ReservationDto> findByTenantName(String tenantName);
    public List<ReservationDto> findByApartmentId(Long apartmentId);
}
