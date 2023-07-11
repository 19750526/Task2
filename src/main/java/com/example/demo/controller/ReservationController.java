package com.example.demo.controller;

import com.example.demo.dto.ChangeReservationDto;
import com.example.demo.dto.CreateReservationDto;
import com.example.demo.dto.ReservationDto;
import com.example.demo.service.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    ResponseEntity<ReservationDto> changeReservation(@PathVariable("id") Long id, @RequestBody @Valid ChangeReservationDto dto) {
        return ResponseEntity.ok(reservationService.changeReservation(id, dto));
    }

    @GetMapping("/simple-search")
    ResponseEntity<List<ReservationDto>> search(@RequestParam(value = "tenantName", required = false) String tenantName,
                                                @RequestParam(value = "apartamentId", required = false) Long apartamentId) {
        if ((tenantName == null && apartamentId == null) || (tenantName != null && apartamentId != null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only one parameter is processed");
        }

        if (tenantName != null) {
            return ResponseEntity.ok(reservationService.findByTenantName(tenantName));
        }

        return ResponseEntity.ok(reservationService.findByApartmentId(apartamentId));
    }

    private <T> ResponseEntity<List<T>> handleEmpty(List<T> body) {
        return body == null || body.isEmpty() ? noContent().build() : ok(body);
    }
}
