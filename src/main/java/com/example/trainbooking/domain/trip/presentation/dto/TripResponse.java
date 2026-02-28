package com.example.trainbooking.domain.trip.presentation.dto;

import com.example.trainbooking.domain.trip.domain.Trip;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class TripResponse {

    private Long tripId;
    private Long trainNo;
    private Long fromStationId;
    private Long toStationId;
    private Timestamp departureTime;
    private Timestamp arrivalTime;

    public TripResponse(Long tripId, Long trainNo, Long fromStationId, Long toStationId, Timestamp departureTime, Timestamp arrivalTime) {
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

