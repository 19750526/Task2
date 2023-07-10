package com.example.demo.util;

import java.time.LocalDate;

public class PeriodUtils {
    public static boolean arePeriodsOverlapping(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }
}
