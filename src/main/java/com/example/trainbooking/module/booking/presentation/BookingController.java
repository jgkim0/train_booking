package com.example.trainbooking.module.booking.presentation;

import com.example.trainbooking.module.booking.application.BookingService;
import com.example.trainbooking.module.booking.presentation.dto.BookingRequest;
import com.example.trainbooking.module.booking.presentation.dto.BookingResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    BookingService bookingService;

    @PostMapping
    public BookingResponse createBooking(@RequestBody BookingRequest bookingRequest){
        return bookingService.createBooking(bookingRequest);
    }

    @GetMapping("/{bookingId}")
    public BookingResponse getBookingInfo(@PathVariable Long bookingId){
        return bookingService.findBooking(bookingId);
    }

    @DeleteMapping("/{bookingId}")
    public void cancleBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
    }


}
