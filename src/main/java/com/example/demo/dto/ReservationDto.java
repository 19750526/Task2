package com.example.demo.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReservationDto extends BaseReservationDto {
    private Long id;

    private String apartment;

    private Long apartmentId;

    private String tenant;

    private double price;
}
