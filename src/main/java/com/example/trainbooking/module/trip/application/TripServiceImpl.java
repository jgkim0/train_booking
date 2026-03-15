package com.example.trainbooking.module.trip.application;

import com.example.trainbooking.module.station.domain.Station;
import com.example.trainbooking.module.station.domain.StationRepository;
import com.example.trainbooking.module.trip.domain.Trip;
import com.example.trainbooking.module.trip.domain.TripRepository;
import com.example.trainbooking.module.trip.presentation.dto.TripRequest;
import com.example.trainbooking.module.trip.presentation.dto.TripResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TripServiceImpl implements TripService{

    private final TripRepository tripRepository;

    private final StationRepository stationRepository;

    @Override
    public List<TripResponse> getTripList() {

        List<TripResponse> responses = tripRepository.findAll().stream().map(TripResponse::from).toList();

        return responses;
    }

    @Override
    public TripResponse getTrip(Long id) {

        TripResponse response = TripResponse.from(tripRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trip 없음")));

        return response;
    }

    @Override
    public TripResponse createTrip(TripRequest request) {

        Station fromStation = stationRepository.getReferenceById(request.getFromStationId());
        Station toStation = stationRepository.getReferenceById(request.getToStationId());

        // 생성자 방식
        Trip trip = Trip.create(request.getTrainNo(), fromStation, toStation, request.getDepartureTime(), request.getArrivalTime());
        Trip saved = tripRepository.save(trip);

        return TripResponse.from(saved);
    }

}
