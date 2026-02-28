package com.example.trainbooking.domain.payment;

import com.example.trainbooking.domain.booking.domain.Booking;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor
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


}
