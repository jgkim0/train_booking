package com.example.trainbooking.module.payment.presentation.dto;

import com.example.trainbooking.module.booking.domain.Booking;
import com.example.trainbooking.module.payment.domain.Payment;
import com.example.trainbooking.module.payment.domain.PaymentStatus;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class PaymentResponse {

    private Long paymentId;
    private Booking booking;
    private int amount;
    private PaymentStatus status;
    private Timestamp paidAt;

    public PaymentResponse(Long paymentId, Booking booking, int amount, PaymentStatus status, Timestamp paidAt) {
        this.paymentId = paymentId;
        this.booking = booking;
        this.amount = amount;
        this.status = status;
        this.paidAt = paidAt;
    }

    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(
                payment.getPaymentId(),
                payment.getBooking(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getPaidAt()
        );
    }
}
