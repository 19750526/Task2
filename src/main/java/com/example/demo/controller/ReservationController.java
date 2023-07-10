package com.example.demo.controller;

import com.example.demo.dto.ReservationDto;
import com.example.demo.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private <T> ResponseEntity<List<T>> handleEmpty(List<T> body) {
        return body == null || body.isEmpty() ? noContent().build() : ok(body);
    }
}
