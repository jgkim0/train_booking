package com.example.trainbooking.module.booking.application;


import com.example.trainbooking.module.booking.presentation.dto.BookingRequest;
import com.example.trainbooking.module.booking.presentation.dto.BookingResponse;

public interface BookingService {

    BookingResponse findBooking(Long id);

    BookingResponse createBooking(BookingRequest bookingRequest);

    void cancelBooking(Long bookingId);
}
