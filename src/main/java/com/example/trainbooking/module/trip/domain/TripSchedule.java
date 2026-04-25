package com.example.trainbooking.module.trip.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Embeddable: 독립적인 PK가 없는 값 타입. Trip의 일부로만 존재하며 별도 테이블을 갖지 않는다.
 * 컬럼은 Trip 테이블에 그대로 매핑된다.
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TripSchedule {

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    public TripSchedule(LocalDateTime departureTime, LocalDateTime arrivalTime) {
        if (!arrivalTime.isAfter(departureTime)) {
            throw new IllegalArgumentException("도착 시간은 출발 시간보다 이후여야 합니다.");
        }
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
