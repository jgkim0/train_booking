package com.example.trainbooking.web;

import com.example.trainbooking.module.seat.application.SeatService;
import com.example.trainbooking.module.seat.presentation.dto.SeatResponse;
import com.example.trainbooking.module.station.application.StationService;
import com.example.trainbooking.module.station.presentation.dto.StationResponse;
import com.example.trainbooking.module.trip.application.TripService;
import com.example.trainbooking.module.trip.presentation.dto.TripResponse;
import com.example.trainbooking.web.dto.TripRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/web/trips")
public class TripViewController {

    private final TripService tripService;
    private final StationService stationService;
    private final SeatService seatService;

    @GetMapping
    public String list(@RequestParam(required = false) Long fromStationId,
                        @RequestParam(required = false) Long toStationId,
                        Model model) {

        List<StationResponse> stations = stationService.getStationList();
        Map<Long, String> stationNames = stations.stream()
                .collect(Collectors.toMap(StationResponse::stationId, StationResponse::stationName));

        List<TripRow> rows = tripService.getTripList().stream()
                .filter(t -> fromStationId == null || fromStationId.equals(t.fromStationId()))
                .filter(t -> toStationId == null || toStationId.equals(t.toStationId()))
                .map(t -> new TripRow(
                        t.tripId(),
                        t.trainNo(),
                        stationNames.getOrDefault(t.fromStationId(), "알 수 없음"),
                        stationNames.getOrDefault(t.toStationId(), "알 수 없음"),
                        t.departureTime(),
                        t.arrivalTime()
                ))
                .toList();

        model.addAttribute("stations", stations);
        model.addAttribute("trips", rows);
        model.addAttribute("fromStationId", fromStationId);
        model.addAttribute("toStationId", toStationId);
        return "trips";
    }

    @GetMapping("/{tripId}/seats")
    public String seats(@PathVariable Long tripId, Model model) {
        TripResponse trip = tripService.getTrip(tripId);

        StationResponse fromStation = stationService.getStationInfo(trip.fromStationId());
        StationResponse toStation = stationService.getStationInfo(trip.toStationId());

        List<SeatResponse> seatList = seatService.findAvailableSeats(tripId);

        model.addAttribute("trip", trip);
        model.addAttribute("fromStationName", fromStation.stationName());
        model.addAttribute("toStationName", toStation.stationName());
        model.addAttribute("seats", seatList);
        return "seats";
    }
}
