package com.example.demo.controller;

import com.example.demo.dto.ChangeReservationDto;
import com.example.demo.dto.CreateReservationDto;
import com.example.demo.dto.ReservationDto;
import com.example.demo.service.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;


    @GetMapping
    ResponseEntity<List<ReservationDto>> allReservation() {
        return handleEmpty(reservationService.allReservations());
    }

    @PostMapping
    ResponseEntity<ReservationDto> makeReservation(@RequestBody @Valid CreateReservationDto dto) {
        return ResponseEntity.ok(reservationService.makeReservation(dto));
    }

    @PutMapping("/{id}")
    ResponseEntity<ReservationDto> changeReservation(@PathVariable Long id, @RequestBody @Valid ChangeReservationDto dto) {
        return ResponseEntity.ok(reservationService.changeReservation(id, dto));
    }

    private <T> ResponseEntity<List<T>> handleEmpty(List<T> body) {
        return body == null || body.isEmpty() ? noContent().build() : ok(body);
    }
}
