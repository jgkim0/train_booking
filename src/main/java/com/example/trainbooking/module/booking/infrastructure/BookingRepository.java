package com.example.trainbooking.module.booking.infrastructure;

import com.example.trainbooking.module.booking.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {


    // 예약 / 굳이 선언 안해도 됨.
    // Booking save(Booking booking);

    // 예약 목록 확인
    List<Booking> findByUserId(Long userId);

    // 예약 취소


    // 예약 결제



}
