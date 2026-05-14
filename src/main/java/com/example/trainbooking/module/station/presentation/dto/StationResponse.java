package com.example.trainbooking.module.station.presentation.dto;

import com.example.trainbooking.module.station.domain.Station;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StationResponse(

        @NotNull
        Long stationId,

        @NotBlank
        String stationName

) {

    public static StationResponse from(Station station) {
        return new StationResponse(
                station.getStationId(),
                station.getStationName()
        );
    }
}
