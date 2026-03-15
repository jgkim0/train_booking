package com.example.trainbooking.module.trip.presentation.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TripSelectOptions {

    private Long fromStationId;
    private LocalDateTime departureTime;

    public TripSelectOptions(Long fromStationId, LocalDateTime departureTime) {
        this.fromStationId = fromStationId;
        this.departureTime = departureTime;
    }
}
