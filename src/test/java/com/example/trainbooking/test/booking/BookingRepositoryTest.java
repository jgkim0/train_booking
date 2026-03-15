package com.example.trainbooking.test.booking;

import com.example.trainbooking.module.booking.domain.Booking;
import com.example.trainbooking.module.booking.infrastructure.BookingRepository;
import com.example.trainbooking.module.booking.domain.BookingStatus;
import com.example.trainbooking.module.seat.domain.Seat;
import com.example.trainbooking.module.seat.domain.SeatRepository;
import com.example.trainbooking.module.station.domain.StationRepository;
import com.example.trainbooking.module.trip.domain.Trip;
import com.example.trainbooking.module.trip.domain.TripRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookingRepositoryTest {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    StationRepository stationRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    SeatRepository seatRepository;

    @Test
    void 좌석_예약(){

        Trip trip = tripRepository.getById(1L);

        Seat seat = seatRepository.getById(1L);

        Booking booking = Booking.builder()
                .userId(1L)
                .trip(trip)
                .status(BookingStatus.CREATED)
                .createdDt(Timestamp.valueOf(LocalDateTime.now()))
                .seat(seat).build();

        Booking saved = bookingRepository.save(booking);

        assertThat(saved.getBookingId()).isNotNull();
    }

    // 트랜잭션 안이면 자동 update 됨... booking.setStatus(BookingStatus.CANCLE)

}
