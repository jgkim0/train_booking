package com.example.trainbooking.module.trip.domain;

import com.example.trainbooking.module.trip.presentation.dto.TripResponse;
import com.example.trainbooking.module.trip.presentation.dto.TripSelectOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    Optional<Trip> findById(Long id);

    // @Embedded 값 타입 경로: t.schedule.departureTime
    @Query("""
            select distinct new com.example.trainbooking.module.trip.presentation.dto.TripSelectOptions(
               fs.stationId,
               t.schedule.departureTime
           )
           from Trip t
           join t.fromStation fs
           """)
    List<TripSelectOptions> findTripSelectOptions();

    @Query("""
            select new com.example.trainbooking.module.trip.presentation.dto.TripResponse(
                        t.tripId,
                        t.trainNo,
                        fs.stationId,
                        ts.stationId,
                        t.schedule.departureTime,
                        t.schedule.arrivalTime
                    )
                    from Trip t
                    join t.fromStation fs
                    join t.toStation ts
                    where fs.stationId = :stationId
                      and t.schedule.departureTime >= :startDate
                      and t.schedule.departureTime < :endDate
           """)
    List<TripResponse> findByStation_StationId_And_DepartureTime(Long stationId, LocalDateTime startDate, LocalDateTime endDate);
}
