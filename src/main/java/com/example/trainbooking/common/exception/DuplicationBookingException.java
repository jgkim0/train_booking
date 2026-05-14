package com.example.trainbooking.common.exception;


public class DuplicationBookingException extends RuntimeException {
    public DuplicationBookingException(String message) {
        super(message);
    }
}
