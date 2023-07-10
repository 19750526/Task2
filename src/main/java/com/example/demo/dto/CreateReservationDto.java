package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReservationDto {
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate stopDate;
    @NotNull
    private Long apartmentId;
    @NotNull
    private Long tenantId;
}
