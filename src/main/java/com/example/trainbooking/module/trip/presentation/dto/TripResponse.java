package com.example.trainbooking.module.trip.presentation.dto;

import com.example.trainbooking.module.trip.domain.Trip;

import java.time.LocalDateTime;

public record TripResponse(Long tripId, Long trainNo, Long fromStationId, Long toStationId, LocalDateTime departureTime,
                           LocalDateTime arrivalTime) {


    public static TripResponse from(Trip trip) {
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

