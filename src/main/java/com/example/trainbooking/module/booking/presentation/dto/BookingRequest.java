package com.example.trainbooking.module.booking.presentation.dto;

import jakarta.validation.constraints.NotNull;

public record BookingRequest(
        @NotNull
        Long userId,
        @NotNull
        Long tripId,
        @NotNull
        Long seatId) {

}
