package com.example.trainbooking.domain.booking.application;

import com.example.trainbooking.domain.booking.domain.Booking;
import com.example.trainbooking.domain.booking.domain.BookingRepository;
import com.example.trainbooking.domain.booking.presentation.dto.BookingRequest;
import com.example.trainbooking.domain.booking.presentation.dto.BookingResponse;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    BookingRepository bookingRepository;

    @Override
    public BookingResponse getBooking(Long id) {
        Booking booking = bookingRepository.findByBookingId(id).orElseThrow(() -> new IllegalArgumentException("Booking 없음"));

        return BookingResponse.from(booking);
    }

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        return null;
    }
}
