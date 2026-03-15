package com.example.trainbooking.module.station.application;

import com.example.trainbooking.module.station.presentation.dto.StationResponse;

import java.util.List;

public interface StationService {


    List<StationResponse> getStationList();

    StationResponse getStationInfo(Long id);
}
