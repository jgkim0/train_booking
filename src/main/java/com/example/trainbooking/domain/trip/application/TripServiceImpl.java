package com.example.trainbooking.domain.trip.application;

import com.example.trainbooking.domain.station.Station;
import com.example.trainbooking.domain.station.StationRepository;
import com.example.trainbooking.domain.trip.domain.Trip;
import com.example.trainbooking.domain.trip.domain.TripRepository;
import com.example.trainbooking.domain.trip.presentation.dto.TripRequest;
import com.example.trainbooking.domain.trip.presentation.dto.TripResponse;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService{

    TripRepository tripRepository;

    StationRepository stationRepository;

    @Override
    public Trip getTrip(Long id) {

        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trip 없음"));

        return trip;
    }

    @Override
    public TripResponse createTrip(TripRequest request) {

        Station fromStation = stationRepository.getReferenceById(request.getFromStationId());
        Station toStation = stationRepository.getReferenceById(request.getToStationId());

        Trip trip = Trip.builder()
                        .trainNo(request.getTrainNo())
                        .fromStation(fromStation)
                        .toStation(toStation)
                        .departureTime(request.getDepartureTime())
                        .arrivalTime(request.getArrivalTime())
                        .build();

        Trip saved = tripRepository.save(trip);

        return TripResponse.from(saved);
    }

}
