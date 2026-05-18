package com.example.trainbooking.module.trip.application;

 import com.example.trainbooking.common.exception.TripNotFoundException;
import com.example.trainbooking.module.station.domain.Station;
import com.example.trainbooking.module.station.domain.StationRepository;
import com.example.trainbooking.module.trip.domain.Trip;
import com.example.trainbooking.module.trip.domain.TripRepository;
import com.example.trainbooking.module.trip.presentation.dto.TripRequest;
import com.example.trainbooking.module.trip.presentation.dto.TripResponse;
  import jakarta.validation.Valid;
 import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

 import java.util.List;

@RequiredArgsConstructor
@Service
public class TripServiceImpl implements TripService{

    private final TripRepository tripRepository;

    private final StationRepository stationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TripResponse> getTripList() {

        return tripRepository.findAll().stream().map(TripResponse::from).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TripResponse getTrip(Long id) {

        return TripResponse.from(tripRepository.findById(id)
                .orElseThrow(() -> new TripNotFoundException("Trip 없음")));
    }

    @Override
    @Transactional
    public TripResponse createTrip(@Valid TripRequest request) {

        Station fromStation = stationRepository.getReferenceById(request.fromStationId());
        Station toStation = stationRepository.getReferenceById(request.toStationId());

        // 생성자 방식
        Trip trip = Trip.create(request.trainNo(), fromStation, toStation, request.departureTime(), request.arrivalTime());
        Trip saved = tripRepository.save(trip);

        return TripResponse.from(saved);
    }

}
