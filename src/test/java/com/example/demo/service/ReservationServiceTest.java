package com.example.demo.service;

import com.example.demo.util.PeriodUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservationServiceTest {


    @Test
    public void testOverlappingPeriods() {
        LocalDate start1 = LocalDate.of(2023, 7, 1);
        LocalDate end1 = LocalDate.of(2023, 7, 10);
        LocalDate start2 = LocalDate.of(2023, 7, 5);
        LocalDate end2 = LocalDate.of(2023, 7, 15);

        boolean overlap = PeriodUtils.arePeriodsOverlapping(start1, end1, start2, end2);
        assertTrue(overlap, "Periods should overlap");
    }

    @Test
    public void testNonOverlappingPeriods() {
        LocalDate start1 = LocalDate.of(2023, 7, 1);
        LocalDate end1 = LocalDate.of(2023, 7, 10);
        LocalDate start2 = LocalDate.of(2023, 7, 15);
        LocalDate end2 = LocalDate.of(2023, 7, 20);

        boolean overlap = PeriodUtils.arePeriodsOverlapping(start1, end1, start2, end2);
        assertFalse(overlap, "Periods should not overlap");
    }

    @Test
    public void testOverlappingPeriodsWithSameStartAndEnd() {
        LocalDate start1 = LocalDate.of(2023, 7, 1);
        LocalDate end1 = LocalDate.of(2023, 7, 10);
        LocalDate start2 = LocalDate.of(2023, 7, 1);
        LocalDate end2 = LocalDate.of(2023, 7, 10);

        boolean overlap = PeriodUtils.arePeriodsOverlapping(start1, end1, start2, end2);
        assertTrue(overlap, "Periods should overlap");
    }

    @Test
    public void testOverlappingPeriodsWithSameStartOrEnd() {
        LocalDate start1 = LocalDate.of(2023, 7, 1);
        LocalDate end1 = LocalDate.of(2023, 7, 10);
        LocalDate start2 = LocalDate.of(2023, 7, 10);
        LocalDate end2 = LocalDate.of(2023, 7, 15);

//        return startA.isBefore(endB) && startB.isBefore(endA);
        boolean overlap = PeriodUtils.arePeriodsOverlapping(start1, end1, start2, end2);
        assertFalse(overlap, "Periods should overlap");
    }

    @Test
    public void testNonOverlappingPeriodsWithAdjacentEnds() {
        LocalDate start1 = LocalDate.of(2023, 7, 1);
        LocalDate end1 = LocalDate.of(2023, 7, 10);
        LocalDate start2 = LocalDate.of(2023, 7, 11);
        LocalDate end2 = LocalDate.of(2023, 7, 15);

        boolean overlap = PeriodUtils.arePeriodsOverlapping(start1, end1, start2, end2);
        assertFalse(overlap, "Periods should not overlap");
    }
}
