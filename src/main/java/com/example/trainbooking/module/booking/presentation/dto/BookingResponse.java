package com.example.trainbooking.module.booking.presentation.dto;

import com.example.trainbooking.module.booking.domain.Booking;
import com.example.trainbooking.module.booking.domain.BookingStatus;
import com.example.trainbooking.module.seat.domain.Seat;
import com.example.trainbooking.module.trip.domain.Trip;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class BookingResponse {

    private Long bookingId;
    private Long userId;
    private Trip trip;
    private BookingStatus status;
    private Timestamp createdDt;
    private Seat seat;

    public BookingResponse(Long bookingId, Long userId, Trip trip, BookingStatus status, Timestamp createdDt, Seat seat) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.trip = trip;
        this.status = status;
        this.createdDt = createdDt;
        this.seat = seat;
    }


    public static BookingResponse from(Booking booking) {
        return new BookingResponse(
                booking.getBookingId(),
                booking.getUserId(),
                booking.getTrip(),
                booking.getStatus(),
                booking.getCreatedDt(),
                booking.getSeat()
        );
    }
}
