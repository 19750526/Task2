package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReservationDto extends BaseReservationDto {
    @NotNull
    private Long apartmentId;
    @NotNull
    private Long tenantId;
}
