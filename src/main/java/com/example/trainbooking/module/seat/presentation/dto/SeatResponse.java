package com.example.trainbooking.module.seat.presentation.dto;

import com.example.trainbooking.module.seat.domain.Seat;
import com.example.trainbooking.module.seat.domain.SeatStatus;

public record SeatResponse(Long seatId, Long seatNo, SeatStatus status, Long tripId) {

    public static SeatResponse from(Seat seat) {
        return new SeatResponse(
                seat.getSeatId(),
                seat.getSeatNo(),
                seat.getStatus(),
                seat.getTrip().getTripId()
        );
    }
}
