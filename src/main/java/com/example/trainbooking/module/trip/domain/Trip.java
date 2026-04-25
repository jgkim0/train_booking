package com.example.trainbooking.module.trip.domain;

import com.example.trainbooking.module.station.domain.Station;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "trip")
@NoArgsConstructor
@Getter
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Long tripId;

    @Column(name = "train_no")
    private Long trainNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_station_id")
    private Station fromStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_station_id")
    private Station toStation;

    /**
     * @Embedded: TripSchedule은 값 타입(@Embeddable)이며 Trip 테이블의 컬럼으로 매핑된다.
     * 객체 지향적으로 스케줄 관련 로직(도착 전 검증 등)을 TripSchedule에 위임할 수 있다.
     */
    @Embedded
    private TripSchedule schedule;

    public static Trip create(
            Long trainNo,
            Station fromStation,
            Station toStation,
            LocalDateTime departureTime,
            LocalDateTime arrivalTime
    ) {
        if (fromStation.equals(toStation)) {
            throw new IllegalArgumentException("출발역과 도착역이 같습니다.");
        }

        Trip trip = new Trip();
        trip.trainNo = trainNo;
        trip.fromStation = fromStation;
        trip.toStation = toStation;
        trip.schedule = new TripSchedule(departureTime, arrivalTime);

        return trip;
    }

    public LocalDateTime getDepartureTime() {
        return schedule.getDepartureTime();
    }

    public LocalDateTime getArrivalTime() {
        return schedule.getArrivalTime();
    }
}
