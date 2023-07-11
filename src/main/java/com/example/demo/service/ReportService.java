package com.example.demo.service;

import com.example.demo.dto.PeriodicReservationReportDto;

import java.time.LocalDate;

public interface ReportService {

    PeriodicReservationReportDto periodicReport(LocalDate startDate, LocalDate stopDate, Long apartmentId);

}
