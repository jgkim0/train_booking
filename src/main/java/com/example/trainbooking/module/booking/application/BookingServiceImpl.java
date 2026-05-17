package com.example.trainbooking.module.booking.application;

import com.example.trainbooking.common.exception.BookingNotFoundException;
import com.example.trainbooking.common.exception.SeatNotFoundException;
import com.example.trainbooking.common.exception.TripNotFoundException;
import com.example.trainbooking.module.booking.domain.Booking;
import com.example.trainbooking.module.booking.domain.BookingStatus;
import com.example.trainbooking.module.booking.infrastructure.BookingRepository;
import com.example.trainbooking.module.booking.presentation.dto.BookingRequest;
import com.example.trainbooking.module.booking.presentation.dto.BookingResponse;
import com.example.trainbooking.module.payment.application.PaymentsService;
import com.example.trainbooking.module.seat.application.SeatService;
import com.example.trainbooking.module.seat.domain.Seat;
import com.example.trainbooking.module.seat.domain.SeatRepository;
import com.example.trainbooking.module.trip.domain.Trip;
import com.example.trainbooking.module.trip.domain.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final TripRepository tripRepository;
    private final SeatRepository seatRepository;

    private final PaymentsService paymentsService;
    private final SeatService seatService;

    @Override
    @Transactional(readOnly = true)
    public BookingResponse findBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("조회된 예약건이 없습니다."));

        return BookingResponse.from(booking);
    }

    @Override
    @Transactional
    public BookingResponse createBooking(BookingRequest bookingRequest) {

        Trip trip = tripRepository.findById(bookingRequest.tripId()).orElseThrow(()-> new TripNotFoundException("조회된 여행정보가 없습니다."));

        Seat seat = seatRepository.findByIdWithLock(bookingRequest.seatId())
                                    .orElseThrow(()-> new SeatNotFoundException("조회된 좌석 정보가 없습니다."));

        // 좌석 점유
        seat.checkBeforeBook();
        seat.book();

        Booking booking = Booking.create(
                bookingRequest.userId(),
                trip,
                seat
        );

        bookingRepository.save(booking);

        // 결제 대기 생성
        paymentsService.createPayment(booking.getBookingId());

        return BookingResponse.from(booking);
    }

    @Override
    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking saved = bookingRepository.findByIdWithLock(bookingId).orElseThrow(() -> new BookingNotFoundException("조회된 예약 건이 없습니다."));

        saved.validateCancelable();

        if (saved.getStatus() == BookingStatus.PAID) {
            paymentsService.cancelPaymentByBooking(bookingId);
        }

        seatService.canceledBookingSeat(saved.getSeat().getSeatId());

        saved.cancel();

    }

}
