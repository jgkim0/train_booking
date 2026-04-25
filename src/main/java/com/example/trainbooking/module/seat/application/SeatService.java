package com.example.trainbooking.module.seat.application;

import com.example.trainbooking.module.seat.presentation.dto.SeatResponse;

import java.util.List;

public interface SeatService {

    public List<SeatResponse> findAvailableSeats(Long tripId);

    public SeatResponse findTripSeatInfo(Long seatId);

    SeatResponse canceledBookingSeat(Long seatId);
}
