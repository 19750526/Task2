package com.example.demo.mapper;

import com.example.demo.domain.Reservation;
import com.example.demo.dto.ReservationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    @Mapping(source = "tenant.name", target = "tenant")
    @Mapping(source = "apartment.name", target = "apartment")
    ReservationDto mapToDto(Reservation entity);
}
