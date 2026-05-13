package com.example.trainbooking.common.exception;


public class StationNotFoundException extends RuntimeException {

    public StationNotFoundException(String message) {
        super(message);
    }
}
