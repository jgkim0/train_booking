package com.example.trainbooking.module.payment.domain;

import com.example.trainbooking.common.BaseEntity;
import com.example.trainbooking.module.booking.domain.Booking;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(name = "amount")
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    @Builder
    public Payment(Booking booking, int amount, PaymentStatus status) {
        this.booking = booking;
        this.amount = amount;
        this.status = status;
    }

    public void ready() {
        this.status = PaymentStatus.READY;
    }

    public void approved() {
        this.status = PaymentStatus.APPROVED;
    }

    public void failed() {
        this.status = PaymentStatus.FAILED;
    }

    public void canceled() {
        this.status = PaymentStatus.CANCELED;
    }

    public static Payment create(Booking booking, int amount) {
        return Payment.builder()
                .booking(booking)
                .amount(amount)
                .status(PaymentStatus.READY)
                .build();
    }
}
