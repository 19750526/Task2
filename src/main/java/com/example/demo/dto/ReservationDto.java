package com.example.demo.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class ReservationDto {
    private Long id;

    private Date startDate;

    private Date stopDate;

    private String apartment;

    private String tenant;

    private double cost;
}
