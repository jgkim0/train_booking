package com.example.trainbooking.common.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(String message) {
        super(message);
    }
}
