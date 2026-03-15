package com.example.trainbooking.test.seat;


import com.example.trainbooking.module.seat.domain.Seat;
import com.example.trainbooking.module.seat.domain.SeatRepository;
import com.example.trainbooking.module.seat.domain.SeatStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SeatRepositoryTest {

    @Autowired
    SeatRepository seatRepository;

    @Test
    void 열차_좌석_현황_확인(){
        List<Seat> seatList = seatRepository.findByTrip_TripIdAndStatus(1L, SeatStatus.AVAILABLE);
        Long seatCount = seatRepository.countByTrip_TripIdAndStatus(1L, SeatStatus.AVAILABLE);

        System.out.println(seatCount);
        assertThat(seatList.size() == seatCount);
    }
}
