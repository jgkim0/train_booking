package com.example.trainbooking.module.trip.presentation.dto;

import java.time.LocalDateTime;

public record TripSelectOptions(Long fromStationId, LocalDateTime departureTime) {

}
