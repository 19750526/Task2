package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PeriodicReservationReportDto extends BaseReservationDto {
    private Integer bookedDaysCount;
    private Integer reservationCount;
}
