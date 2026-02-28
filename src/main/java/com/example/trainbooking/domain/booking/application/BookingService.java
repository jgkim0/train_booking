package com.example.trainbooking.domain.booking.application;


import com.example.trainbooking.domain.booking.presentation.dto.BookingRequest;
import com.example.trainbooking.domain.booking.presentation.dto.BookingResponse;

public interface BookingService {

    BookingResponse getBooking(Long id);

    BookingResponse createBooking(BookingRequest bookingRequest);
}
