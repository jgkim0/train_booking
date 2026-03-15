package com.example.trainbooking.module.seat.presentation.dto;

import com.example.trainbooking.module.seat.domain.Seat;
import com.example.trainbooking.module.seat.domain.SeatStatus;
import lombok.Getter;

@Getter
public class SeatResponse {

    private Long seatId;
    private Long seatNo;
    private SeatStatus status;
    private Long tripId;

    public SeatResponse(Long seatId, Long seatNo, SeatStatus status, Long tripId) {
        this.seatId = seatId;
        this.seatNo = seatNo;
        this.status = status;
        this.tripId = tripId;
    }

    public static SeatResponse from(Seat seat) {
        return new SeatResponse(
                seat.getSeatId(),
                seat.getSeatNo(),
                seat.getStatus(),
                seat.getTrip().getTripId()
        );
    }
}
