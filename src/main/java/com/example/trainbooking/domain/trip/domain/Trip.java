package com.example.trainbooking.domain.trip.domain;

import com.example.trainbooking.domain.station.Station;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

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
        private Timestamp departureTime;

        @Column(name = "arrival_time")
        private Timestamp  arrivalTime;

    @Builder
    public Trip(Long trainNo, Station fromStation, Station toStation, Timestamp departureTime, Timestamp arrivalTime) {
        this.trainNo = trainNo;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
