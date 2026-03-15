package com.example.trainbooking.module.station.presentation;

import com.example.trainbooking.module.station.application.StationService;
import com.example.trainbooking.module.station.presentation.dto.StationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stations")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @GetMapping
    public List<StationResponse> getStationList(){
        return stationService.getStationList();
    }

    @GetMapping("/{id}")
    public StationResponse getStationInfo(@PathVariable Long id){

        return stationService.getStationInfo(id);
    }
}