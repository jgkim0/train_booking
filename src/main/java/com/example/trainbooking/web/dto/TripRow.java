package com.example.trainbooking.web.dto;

import java.time.LocalDateTime;

public record TripRow(
        Long tripId,
        Long trainNo,
        String fromStationName,
        String toStationName,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime
) {
}
