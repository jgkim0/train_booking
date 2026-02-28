package com.example.trainbooking.domain.trip.application;

import com.example.trainbooking.domain.trip.domain.Trip;
import com.example.trainbooking.domain.trip.presentation.dto.TripRequest;
import com.example.trainbooking.domain.trip.presentation.dto.TripResponse;

public interface TripService {

    public Trip getTrip(Long id);

    public TripResponse createTrip(TripRequest tripRequest);
}
