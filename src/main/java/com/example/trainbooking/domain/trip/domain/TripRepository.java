package com.example.trainbooking.domain.trip.domain;

import com.example.trainbooking.domain.trip.presentation.dto.TripRequest;
import com.example.trainbooking.domain.trip.presentation.dto.TripResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    // Spring Data JPA가 Optional 쓰는 이유

    Optional<Trip> findById(Long id);

    Trip save(Trip trip);


}