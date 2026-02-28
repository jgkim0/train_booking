package com.example.trainbooking.domain.booking.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingId(Long bookingId);

    // 굳이 선언 안해도 됨.
//    Booking save(Booking booking);

}
