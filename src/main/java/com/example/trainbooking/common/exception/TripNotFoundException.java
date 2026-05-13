package com.example.trainbooking.common.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TripNotFoundException extends RuntimeException {

    public TripNotFoundException(String message) {
        super(message);
    }
}
