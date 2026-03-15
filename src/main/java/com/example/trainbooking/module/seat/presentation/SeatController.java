package com.example.trainbooking.module.seat.presentation;

import com.example.trainbooking.module.seat.application.SeatService;
import com.example.trainbooking.module.seat.presentation.dto.SeatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/trips/{tripId}/seat")
    public List<SeatResponse> getSeatListFromTrip(@PathVariable Long tripId){
        return seatService.findAvailableSeats(tripId);
    }


    @GetMapping("/seats/{seatId}")
    public SeatResponse getSeatInfo(@PathVariable Long seatId) {
        return seatService.findTripSeatInfo(seatId);
    }



}
