package com.example.trainbooking.module.booking.domain;


import com.example.trainbooking.module.seat.domain.Seat;
import com.example.trainbooking.module.trip.domain.Trip;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="booking_id")
    private Long bookingId;

    @Column(name="user_id")
    private Long userId;

//    @Column(name="trip_id")
    @ManyToOne
    @JoinColumn(name="trip_id")
    private Trip trip;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private BookingStatus status;

    @Column(name="created_dt")
    private Timestamp createdDt;

    @ManyToOne
    @JoinColumn(name="seat_id")
    private Seat seat;

    @Builder
    public Booking(Long userId, Trip trip, BookingStatus status, Timestamp createdDt, Seat seat) {
        this.userId = userId;
        this.trip = trip;
        this.status = status;
        this.createdDt = createdDt;
        this.seat = seat;
    }

    public void cancle(){
        this.status = BookingStatus.CANCELED;
    }

    public void booked() {
        this.status = BookingStatus.CREATED;
    }

    public void paid() {
        this.status = BookingStatus.PAID;
    }

    public static Booking create(Long userId, Trip trip, Seat seat) {

        return Booking.builder()
                .userId(userId)
                .createdDt(new Timestamp(System.currentTimeMillis()))
                .status(BookingStatus.CREATED)
                .trip(trip)
                .seat(seat)
                .build();
    }

    public void validateCancelable() {
        if (status == BookingStatus.CANCELED) {
            throw new IllegalStateException("이미 취소된 예약입니다.");
        }
    }

}
