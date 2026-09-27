package com.example.trainbooking.common.exception;

public class AlreadyEmptySeatException extends RuntimeException {
    public AlreadyEmptySeatException(String message) {
        super(message);
    }
}
