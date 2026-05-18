package com.example.trainbooking.module.booking.infrastructure;

import com.example.trainbooking.module.booking.domain.Booking;
 import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // 예약 목록 확인
    List<Booking> findByUserId(Long userId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(" select b from Booking b where b.bookingId = :bookingId")
    Optional<Booking> findByIdWithLock(@Param("bookingId") Long bookingId);

    // 예약 취소


    // 예약 결제



}
