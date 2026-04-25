package com.example.trainbooking.module.seat.domain;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//ctrl+alt+o


@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    // 예약 시에만 비관적 락 적용 — 일반 findById는 JpaRepository 기본 메서드(락 없음) 사용
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Seat s where s.seatId = :seatId")
    Optional<Seat> findByIdWithLock(@Param("seatId") Long seatId);

    List<Seat> findByTrip_TripIdAndStatus(Long tripId, SeatStatus status);

    Long countByTrip_TripIdAndStatus(Long tripId, SeatStatus status);
}
