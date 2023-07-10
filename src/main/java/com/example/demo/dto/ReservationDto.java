package com.example.demo.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class ReservationDto {
    private Long id;

    private LocalDate startDate;

    private LocalDate stopDate;

    private String apartment;

    private String tenant;

    private double price;
}
