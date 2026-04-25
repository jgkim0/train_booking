package com.example.trainbooking.module.booking.domain;

import com.example.trainbooking.common.BaseEntity;
import com.example.trainbooking.module.seat.domain.Seat;
import com.example.trainbooking.module.trip.domain.Trip;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "booking")
public class Booking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Builder
    public Booking(Long userId, Trip trip, BookingStatus status, Seat seat) {
        this.userId = userId;
        this.trip = trip;
        this.status = status;
        this.seat = seat;
    }

    public void cancel() {
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
