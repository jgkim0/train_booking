package com.example.trainbooking.test.trip;

import com.example.trainbooking.module.station.domain.Station;
import com.example.trainbooking.module.station.domain.StationRepository;
import com.example.trainbooking.module.trip.domain.Trip;
import com.example.trainbooking.module.trip.domain.TripRepository;
import com.example.trainbooking.module.trip.presentation.dto.TripResponse;
import com.example.trainbooking.module.trip.presentation.dto.TripSelectOptions;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TripRepositoryTest {


    @Autowired
    TripRepository tripRepository;
    @Autowired
    StationRepository stationRepository;

    EntityManager entityManager;

    @Test
    void trip_조회() {
        tripRepository.findById(1L);
    }

    @Test
    void trip_저장() {

        LocalDateTime date = LocalDateTime.now();

        Station toStation = stationRepository.getReferenceById(1L);
        Station fromStation = stationRepository.getReferenceById(2L);

        Trip trip = Trip.builder()
                .arrivalTime(date)
                .departureTime(date)
                .toStation(toStation)
                .fromStation(fromStation)
                .trainNo(1L)
                .build();

        Trip saved = tripRepository.save(trip);
        tripRepository.flush();   // 👈 핵심

        Trip found = tripRepository.findById(saved.getTripId())
                .orElseThrow();

        assertThat(saved.getTripId()).isNotNull();
        assertThat(found.getTrainNo()).isEqualTo(1L);
        assertThat(found.getFromStation().getStationId()).isEqualTo(2L);
        assertThat(found.getToStation().getStationId()).isEqualTo(1L);

    }

    @Test
    void trip_검색옵션_조회() {
        List<TripSelectOptions> tripSelectOptions = tripRepository.findTripSelectOptions();

        assertThat(tripSelectOptions.get(1).getDepartureTime()).isNotNull();
    }

    @Test
    void trip_검색_조회() {
        LocalDate targetDate = LocalDate.parse("20260130", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDateTime start = targetDate.atStartOfDay();
        LocalDateTime end = targetDate.plusDays(1).atStartOfDay();
        List<TripResponse> tripSelectOptions = tripRepository.findByStation_StationId_And_DepartureTime(5L, start, end);

//        assertThat(tripSelectOptions).isNotEmpty();
        assertThat(tripSelectOptions.get(0).getDepartureTime()).isNotNull();
    }
}
