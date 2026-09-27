package com.example.trainbooking.test.booking;

import com.example.trainbooking.common.exception.DuplicationBookingException;
import com.example.trainbooking.module.booking.application.BookingService;
import com.example.trainbooking.module.booking.infrastructure.BookingRepository;
import com.example.trainbooking.module.booking.presentation.dto.BookingRequest;
import com.example.trainbooking.module.booking.presentation.dto.BookingResponse;
import com.example.trainbooking.module.payment.domain.PaymentRepository;
import com.example.trainbooking.module.seat.domain.Seat;
import com.example.trainbooking.module.seat.domain.SeatRepository;
import com.example.trainbooking.module.seat.domain.SeatStatus;
import com.example.trainbooking.module.station.domain.Station;
import com.example.trainbooking.module.station.domain.StationRepository;
import com.example.trainbooking.module.trip.domain.Trip;
import com.example.trainbooking.module.trip.domain.TripRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 좌석 중복 예약(Double Booking) 방지 검증용 동시성 테스트.
 *
 * BookingServiceImpl.createBooking() 은 SeatRepository.findByIdWithLock() 으로
 * PESSIMISTIC_WRITE 락을 건 뒤 좌석 상태를 확인하고 예약을 생성한다.
 * 같은 좌석에 여러 스레드가 동시에 예약을 시도하면, 먼저 락을 잡은 스레드만 성공하고
 * 나머지는 좌석이 이미 BOOKED 상태임을 확인하고 DuplicationBookingException 을 던져야 한다.
 *
 * 락은 실제 DB(row-level lock)에 의존하므로, H2 인메모리 DB가 아니라
 * application-local.yml 이 가리키는 실제 MariaDB 를 사용하는 SpringBootTest 로 작성한다.
 * (다른 *RepositoryTest 들도 동일한 방식으로 실제 DB를 사용한다.)
 */
@SpringBootTest
class BookingConcurrencyTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    private Long fromStationId;
    private Long toStationId;
    private Long tripId;
    private Long seatId;
    private Long createdBookingId;

    @BeforeEach
    void setUp() {
        Station fromStation = stationRepository.save(new Station(null, "동시성테스트_출발역"));
        Station toStation = stationRepository.save(new Station(null, "동시성테스트_도착역"));
        fromStationId = fromStation.getStationId();
        toStationId = toStation.getStationId();

        Trip trip = Trip.create(
                999999L,
                fromStation,
                toStation,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(2)
        );
        trip = tripRepository.save(trip);
        tripId = trip.getTripId();

        Seat seat = seatRepository.save(new Seat(1L, SeatStatus.AVAILABLE, trip));
        seatId = seat.getSeatId();

        createdBookingId = null;
    }

    @AfterEach
    void tearDown() {
        if (createdBookingId != null) {
            paymentRepository.findByBooking_BookingId(createdBookingId)
                    .ifPresent(paymentRepository::delete);
            bookingRepository.deleteById(createdBookingId);
        }
        seatRepository.deleteById(seatId);
        tripRepository.deleteById(tripId);
        stationRepository.deleteById(fromStationId);
        stationRepository.deleteById(toStationId);
    }

    @Test
    void 같은_좌석에_동시_예약_요청시_한_건만_성공한다() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch readyLatch = new CountDownLatch(threadCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(threadCount);

        List<Future<BookingResponse>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            long userId = 9_000_000L + i;
            futures.add(executorService.submit(() -> {
                readyLatch.countDown();
                startLatch.await();
                try {
                    return bookingService.createBooking(new BookingRequest(userId, tripId, seatId));
                } finally {
                    doneLatch.countDown();
                }
            }));
        }

        // 모든 스레드가 대기 상태로 진입한 뒤 동시에 출발시킨다.
        readyLatch.await();
        startLatch.countDown();

        boolean finished = doneLatch.await(15, TimeUnit.SECONDS);
        executorService.shutdown();
        assertThat(finished).as("모든 예약 요청이 제한 시간 내에 끝나야 한다").isTrue();

        int successCount = 0;
        int conflictCount = 0;

        for (Future<BookingResponse> future : futures) {
            try {
                BookingResponse response = future.get();
                successCount++;
                createdBookingId = response.bookingId();
            } catch (ExecutionException e) {
                assertThat(e.getCause()).isInstanceOf(DuplicationBookingException.class);
                conflictCount++;
            }
        }

        // 정확히 한 건만 성공하고 나머지는 전부 중복 예약 예외로 실패해야 한다.
        assertThat(successCount).isEqualTo(1);
        assertThat(conflictCount).isEqualTo(threadCount - 1);

        Seat updatedSeat = seatRepository.findById(seatId).orElseThrow();
        assertThat(updatedSeat.getStatus()).isEqualTo(SeatStatus.BOOKED);

        assertThat(bookingRepository.findById(createdBookingId)).isPresent();
    }
}
