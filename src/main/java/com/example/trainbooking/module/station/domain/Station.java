package com.example.trainbooking.module.station.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "station")
@NoArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long stationId;

    @Column(name = "station_name")
    private String stationName;

    public Station(Long stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
    }


}
