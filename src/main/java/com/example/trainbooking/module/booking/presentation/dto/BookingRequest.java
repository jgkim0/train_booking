package com.example.trainbooking.module.booking.presentation.dto;

import com.example.trainbooking.module.booking.domain.BookingStatus;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class BookingRequest {

    private Long bookingId;
    private Long userId;
    private Long tripId;
    private BookingStatus status;
    private Timestamp createdDt;
    private Long seatId;

    public BookingRequest(Long bookingId, Long userId, Long tripId, BookingStatus status, Timestamp createdDt, Long seatId) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.tripId = tripId;
        this.status = status;
        this.createdDt = createdDt;
        this.seatId = seatId;
    }
}
