package com.example.trainbooking.module.payment.presentation.dto;

import com.example.trainbooking.module.payment.domain.Payment;
import com.example.trainbooking.module.payment.domain.PaymentStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PaymentResponse {

    private Long paymentId;
    private Long bookingId;
    private int amount;
    private PaymentStatus status;
    private LocalDateTime createdAt;

    public PaymentResponse(Long paymentId, Long bookingId, int amount, PaymentStatus status, LocalDateTime createdAt) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

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
