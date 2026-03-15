package com.example.trainbooking.module.seat.domain;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//ctrl+alt+o


@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    // 동시성 문제??
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Seat> findById(Long seatId);

    List<Seat> findByTrip_TripIdAndStatus(Long tripId, SeatStatus status);

    // 예매 가능한 좌석 카운트
    Long countByTrip_TripIdAndStatus(Long tripId, SeatStatus status);

    // 좌석 상태 업데이트
    // JPA 더티 체킹 활용하여 BookingService createBook 에서 처리.


}
