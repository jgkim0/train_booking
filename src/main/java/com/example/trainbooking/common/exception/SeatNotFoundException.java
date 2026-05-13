package com.example.trainbooking.common.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SeatNotFoundException extends RuntimeException {

    public SeatNotFoundException(String message) {
        super(message);
    }
}
