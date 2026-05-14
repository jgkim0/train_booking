package com.example.trainbooking.module.booking.presentation.dto;

import com.example.trainbooking.module.booking.domain.Booking;
import com.example.trainbooking.module.booking.domain.BookingStatus;

import java.time.LocalDateTime;

public record BookingResponse(Long bookingId, Long userId, Long tripId, BookingStatus status, LocalDateTime createdAt,
                              Long seatId) {

    public static BookingResponse from(Booking booking) {
        return new BookingResponse(
                booking.getBookingId(),
                booking.getUserId(),
                booking.getTrip().getTripId(),
                booking.getStatus(),
                booking.getCreatedAt(),
                booking.getSeat().getSeatId()
        );
    }
}
