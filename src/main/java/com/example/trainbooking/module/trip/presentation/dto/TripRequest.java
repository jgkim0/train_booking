package com.example.trainbooking.module.trip.presentation.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TripRequest {

    private Long trainNo;
    private Long fromStationId;
    private Long toStationId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public TripRequest(Long trainNo, Long fromStationId, Long toStationId, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.trainNo = trainNo;
        this.fromStationId = fromStationId;
        this.toStationId = toStationId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}

