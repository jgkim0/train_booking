package com.example.trainbooking.module.station.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    Optional<Station> findById(Long id);


}
