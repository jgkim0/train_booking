package com.example.trainbooking.module.payment.domain;

import com.example.trainbooking.module.booking.domain.Booking;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private Long paymentId;

//    @Column(name="booking_id")
    @ManyToOne
    @JoinColumn(name="booking_id")
    private Booking booking;

    @Column(name="amonut")
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private PaymentStatus status;

    @Column(name="paid_at")
    private Timestamp paidAt;

    @Builder
    public Payment(Booking booking, int amount, PaymentStatus status, Timestamp paidAt) {
        this.booking = booking;
        this.amount = amount;
        this.status = status;
        this.paidAt = paidAt;
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

    public void cancled() {
        this.status = PaymentStatus.CANCLED;
    }

}
