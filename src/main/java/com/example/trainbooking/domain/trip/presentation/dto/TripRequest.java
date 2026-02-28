package com.example.trainbooking.domain.trip.presentation.dto;

import com.example.trainbooking.domain.trip.domain.Trip;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class TripRequest {

    private Long trainNo;
    private Long fromStationId;
    private Long toStationId;
    private Timestamp departureTime;
    private Timestamp arrivalTime;

    public TripRequest(Long trainNo, Long fromStationId, Long toStationId, Timestamp departureTime, Timestamp arrivalTime) {
        this.trainNo = trainNo;
        this.fromStationId = fromStationId;
        this.toStationId = toStationId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}

