package com.example.demo.service;

import com.example.demo.domain.Apartment;
import com.example.demo.domain.Reservation;
import com.example.demo.dto.PeriodicReservationReportDto;
import com.example.demo.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReportServiceTest {
    @Mock
    private ReservationRepository reservationRepository;

    private ReportService reportService;

    public ReportServiceTest() {
        MockitoAnnotations.initMocks(this);
        reportService = new ReportServiceImpl(reservationRepository);
    }

    @Test
    void testGenerateReport() {
        LocalDate startDate = LocalDate.of(2023, 7, 1);
        LocalDate stopDate = LocalDate.of(2023, 7, 10);
        Apartment apartment1 = new Apartment();
        apartment1.setId(1L);

        Reservation reservation1 = new Reservation();
        reservation1.setStartDate(LocalDate.of(2023, 7, 2));
        reservation1.setStopDate(LocalDate.of(2023, 7, 4));
        reservation1.setApartment(apartment1);

        Reservation reservation2 = new Reservation();
        reservation2.setStartDate(LocalDate.of(2023, 7, 6));
        reservation2.setStopDate(LocalDate.of(2023, 7, 9));
        reservation2.setApartment(apartment1);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);

        when(reservationRepository.findByStartDateGreaterThanEqualAndStopDateLessThanEqualAndAndApartment_Id(startDate, stopDate, apartment1.getId())).thenReturn(reservations);

        PeriodicReservationReportDto report = reportService.periodicReport(startDate, stopDate, apartment1.getId());

        assertEquals(7, report.getBookedDaysCount());
        assertEquals(2, report.getReservationCount());

        verify(reservationRepository, times(1)).findByStartDateGreaterThanEqualAndStopDateLessThanEqualAndAndApartment_Id(startDate, stopDate, apartment1.getId());
        verifyNoMoreInteractions(reservationRepository);
    }
}
