package com.example.trainbooking.module.trip.application;

import com.example.trainbooking.module.trip.presentation.dto.TripRequest;
import com.example.trainbooking.module.trip.presentation.dto.TripResponse;

import java.util.List;

public interface TripService {

    public List<TripResponse> getTripList();

    public TripResponse getTrip(Long id);

    public TripResponse createTrip(TripRequest tripRequest);
}
