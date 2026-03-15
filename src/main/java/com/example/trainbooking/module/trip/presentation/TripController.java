package com.example.trainbooking.module.trip.presentation;


import com.example.trainbooking.module.trip.presentation.dto.TripResponse;
import com.example.trainbooking.module.trip.application.TripService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }


    @GetMapping
    public List<TripResponse> getTripList() {
        List<TripResponse> tripList = tripService.getTripList();
        return tripList;
    }

    @GetMapping("/{id}")
    public TripResponse getTrip(@PathVariable Long id) {
        TripResponse trip = tripService.getTrip(id);
        return trip;
    }
}
