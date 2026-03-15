package com.example.trainbooking.module.booking.application;

import com.example.trainbooking.module.booking.domain.Booking;
import com.example.trainbooking.module.booking.domain.BookingStatus;
import com.example.trainbooking.module.booking.infrastructure.BookingRepository;
import com.example.trainbooking.module.booking.presentation.dto.BookingRequest;
import com.example.trainbooking.module.booking.presentation.dto.BookingResponse;
import com.example.trainbooking.module.payment.application.PaymentsService;
import com.example.trainbooking.module.seat.domain.Seat;
import com.example.trainbooking.module.seat.domain.SeatRepository;
import com.example.trainbooking.module.trip.domain.Trip;
import com.example.trainbooking.module.trip.domain.TripRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public BookingResponse findBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Booking 없음"));

        return BookingResponse.from(booking);
    }

    @Override
    @Transactional
    public BookingResponse createBooking(BookingRequest bookingRequest) {

        Trip trip = tripRepository.getReferenceById(bookingRequest.getTripId());

        Seat seat = seatRepository.findById(bookingRequest.getSeatId())
                                    .orElseThrow(()-> new EntityNotFoundException("조회된 좌석 정보가 없습니다."));

        seat.checkBeforeBook();

        seat.book();

        Booking booking = Booking.create(
                bookingRequest.getUserId(),
                trip,
                seat
        );

        bookingRepository.save(booking);

        return BookingResponse.from(booking);
    }

    @Transactional
    @Override
    public void cancelBooking(Long bookingId) {
        Booking saved = bookingRepository.findById(bookingId).orElseThrow(()->new EntityNotFoundException("조회된 예약건이 없습니다."));

        saved.validateCancelable();

        if (saved.getStatus() == BookingStatus.PAID) {
            paymentsService.cancelPaymentByBooking(bookingId);
        }

        saved.cancle();


    }

}
