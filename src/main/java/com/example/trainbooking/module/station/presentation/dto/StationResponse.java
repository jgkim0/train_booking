package com.example.trainbooking.module.station.presentation.dto;

import com.example.trainbooking.module.station.domain.Station;
import lombok.Getter;

@Getter
public class StationResponse {

    private Long stationId;
    private String stationName;

    public StationResponse(Long stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
    }

    public static StationResponse from(Station station){
        return new StationResponse(
                station.getStationId(),
                station.getStationName()
        );
    }
}
