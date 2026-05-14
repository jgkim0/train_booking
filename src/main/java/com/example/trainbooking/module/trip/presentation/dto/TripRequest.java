package com.example.trainbooking.module.trip.presentation.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TripRequest(
            @NotNull Long trainNo
          , @NotNull Long fromStationId
          , @NotNull Long toStationId
          , @NotNull LocalDateTime departureTime
          , @NotNull LocalDateTime arrivalTime
    ) {

}

