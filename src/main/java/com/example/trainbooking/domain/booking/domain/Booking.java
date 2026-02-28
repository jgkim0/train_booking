package com.example.trainbooking.domain.booking.domain;


import com.example.trainbooking.domain.seat.Seat;
import com.example.trainbooking.domain.trip.domain.Trip;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor
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

}
