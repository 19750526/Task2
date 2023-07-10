package com.example.demo.exception;

public class UnableToRegisterReservation extends RuntimeException {
    public UnableToRegisterReservation(String message) {
        super(message);
    }
}
