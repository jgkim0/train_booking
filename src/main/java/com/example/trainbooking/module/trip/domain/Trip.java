package com.example.trainbooking.module.trip.domain;

import com.example.trainbooking.module.station.domain.Station;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="trip")
@NoArgsConstructor
@Getter
    public class Trip {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "trip_id")
        private Long tripId;

        @Column(name = "train_no")
        private Long trainNo;

        // 차후를 위한 FK
//    @Column(name = "from_station_id")
//    private Long fromStationId;
//
//    @Column(name = "to_station_id")
//    private Long toStationId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "from_station_id")
        private Station fromStation;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "to_station_id")
        private Station toStation;


        @Column(name = "departure_time")
        private LocalDateTime departureTime;

        @Column(name = "arrival_time")
        private LocalDateTime  arrivalTime;

    @Builder
    public Trip(Long trainNo, Station fromStation, Station toStation, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.trainNo = trainNo;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }


    public static Trip create(
            Long trainNo,
            Station fromStation,
            Station toStation,
            LocalDateTime departureTime,
            LocalDateTime arrivalTime
    ){

        if(fromStation.equals(toStation)) {
            throw new IllegalArgumentException("출발역과 도착역이 같습니다.");
        }

        Trip trip = new Trip();
        trip.trainNo = trainNo;
        trip.fromStation = fromStation;
        trip.toStation = toStation;
        trip.departureTime = departureTime;
        trip.arrivalTime = arrivalTime;

        return trip;

    };
}
