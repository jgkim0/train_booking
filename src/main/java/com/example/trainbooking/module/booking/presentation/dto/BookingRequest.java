package com.example.trainbooking.module.booking.presentation.dto;

import lombok.Getter;

@Getter
public class BookingRequest {

    private Long userId;
    private Long tripId;
    private Long seatId;

    public BookingRequest(Long userId, Long tripId, Long seatId) {
        this.userId = userId;
        this.tripId = tripId;
        this.seatId = seatId;
    }
}
