package com.example.trainbooking.module.payment.presentation.dto;

import com.example.trainbooking.module.payment.domain.Payment;
import com.example.trainbooking.module.payment.domain.PaymentStatus;

import java.time.LocalDateTime;

public record PaymentResponse(Long paymentId, Long bookingId, int amount, PaymentStatus status,
                              LocalDateTime createdAt) {

    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(
                payment.getPaymentId(),
                payment.getBooking().getBookingId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getCreatedAt()
        );
    }
}
