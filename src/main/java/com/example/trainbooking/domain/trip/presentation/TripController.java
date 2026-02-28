package com.example.trainbooking.domain.trip.presentation;


import com.example.trainbooking.domain.trip.presentation.dto.TripResponse;
import com.example.trainbooking.domain.trip.application.TripService;
import com.example.trainbooking.domain.trip.domain.Trip;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/{id}")
    public TripResponse getTrips(@PathVariable Long id) {
        Trip trip = tripService.getTrip(id);
        return TripResponse.from(trip);
    }
}
