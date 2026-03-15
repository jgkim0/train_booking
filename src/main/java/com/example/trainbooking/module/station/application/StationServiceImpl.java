package com.example.trainbooking.module.station.application;

import com.example.trainbooking.module.station.domain.Station;
import com.example.trainbooking.module.station.domain.StationRepository;
import com.example.trainbooking.module.station.presentation.dto.StationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    @Override
    public List<StationResponse> getStationList() {

        List<StationResponse> response = new ArrayList<>();

        for (Station station : stationRepository.findAll()) {
            StationResponse from = StationResponse.from(station);
            response.add(from);
        }
        return response;
    }

    @Override
    public StationResponse getStationInfo(Long id) {
        return StationResponse.from(stationRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("출발역이 존재하지 않습니다.")));

    }
}
