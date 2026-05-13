package com.example.trainbooking.common.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DuplicationBookingException extends RuntimeException {
    public DuplicationBookingException(String message) {
        super(message);
    }
}
