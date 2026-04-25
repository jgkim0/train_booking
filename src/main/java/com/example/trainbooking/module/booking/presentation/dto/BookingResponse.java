package com.example.trainbooking.module.booking.presentation.dto;

import com.example.trainbooking.module.booking.domain.Booking;
import com.example.trainbooking.module.booking.domain.BookingStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookingResponse {

    private Long bookingId;
    private Long userId;
    private Long tripId;
    private BookingStatus status;
    private LocalDateTime createdAt;
    private Long seatId;

    public BookingResponse(Long bookingId, Long userId, Long tripId, BookingStatus status, LocalDateTime createdAt, Long seatId) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.tripId = tripId;
        this.status = status;
        this.createdAt = createdAt;
        this.seatId = seatId;
    }

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
