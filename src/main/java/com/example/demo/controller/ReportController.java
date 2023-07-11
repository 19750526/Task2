package com.example.demo.controller;

import com.example.demo.dto.PeriodicReservationReportDto;
import com.example.demo.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/periodic")
    public ResponseEntity<PeriodicReservationReportDto> generateReport(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("stopDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate stopDate,
            @RequestParam("apartmentId") Long apartmentId) {
        return ResponseEntity.ok(reportService.periodicReport(startDate, stopDate, apartmentId));
    }

}
