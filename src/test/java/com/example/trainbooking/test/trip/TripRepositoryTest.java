package com.example.trainbooking.test.trip;

import com.example.trainbooking.domain.station.Station;
import com.example.trainbooking.domain.station.StationRepository;
import com.example.trainbooking.domain.trip.domain.Trip;
import com.example.trainbooking.domain.trip.domain.TripRepository;
import com.example.trainbooking.domain.trip.presentation.dto.TripRequest;
import com.example.trainbooking.domain.trip.presentation.dto.TripResponse;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;

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

        Timestamp date = new Timestamp(System.currentTimeMillis());

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

//        entityManager.flush();
//        entityManager.clear();
    }
}
