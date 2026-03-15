package com.example.trainbooking.module.trip.presentation.dto;

import com.example.trainbooking.module.trip.domain.Trip;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TripResponse {

    private Long tripId;
    private Long trainNo;
    private Long fromStationId;
    private Long toStationId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public TripResponse(Long tripId, Long trainNo, Long fromStationId, Long toStationId, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.tripId = tripId;
        this.trainNo = trainNo;
        this.fromStationId = fromStationId;
        this.toStationId = toStationId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }


    public static TripResponse from(Trip trip){
        return new TripResponse(
                trip.getTripId(),
                trip.getTrainNo(),
                trip.getFromStation().getStationId(),
                trip.getToStation().getStationId(),
                trip.getDepartureTime(),
                trip.getArrivalTime()
        );
    }
}

