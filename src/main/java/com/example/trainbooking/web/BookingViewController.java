package com.example.trainbooking.web;

import com.example.trainbooking.module.booking.application.BookingService;
import com.example.trainbooking.module.booking.domain.BookingStatus;
import com.example.trainbooking.module.booking.presentation.dto.BookingRequest;
import com.example.trainbooking.module.booking.presentation.dto.BookingResponse;
import com.example.trainbooking.module.payment.application.PaymentsService;
import com.example.trainbooking.module.payment.presentation.dto.PaymentResponse;
import com.example.trainbooking.module.seat.application.SeatService;
import com.example.trainbooking.module.seat.presentation.dto.SeatResponse;
import com.example.trainbooking.module.station.application.StationService;
import com.example.trainbooking.module.trip.application.TripService;
import com.example.trainbooking.module.trip.presentation.dto.TripResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/web/bookings")
public class BookingViewController {

    private final BookingService bookingService;
    private final PaymentsService paymentsService;
    private final SeatService seatService;
    private final TripService tripService;
    private final StationService stationService;

    @GetMapping("/new")
    public String newBookingForm(@RequestParam Long tripId, @RequestParam Long seatId, Model model) {
        TripResponse trip = tripService.getTrip(tripId);
        SeatResponse seat = seatService.findTripSeatInfo(seatId);

        model.addAttribute("trip", trip);
        model.addAttribute("seat", seat);
        model.addAttribute("fromStationName", stationService.getStationInfo(trip.fromStationId()).stationName());
        model.addAttribute("toStationName", stationService.getStationInfo(trip.toStationId()).stationName());
        return "booking-new";
    }

    @PostMapping
    public String create(@RequestParam Long userId, @RequestParam Long tripId, @RequestParam Long seatId,
                          RedirectAttributes redirectAttributes) {
        try {
            BookingResponse booking = bookingService.createBooking(new BookingRequest(userId, tripId, seatId));
            return "redirect:/web/bookings/" + booking.bookingId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/web/trips/" + tripId + "/seats";
        }
    }

    @GetMapping("/{bookingId}")
    public String detail(@PathVariable Long bookingId, Model model) {
        BookingResponse booking = bookingService.findBooking(bookingId);
        TripResponse trip = tripService.getTrip(booking.tripId());
        SeatResponse seat = seatService.findTripSeatInfo(booking.seatId());

        model.addAttribute("booking", booking);
        model.addAttribute("trip", trip);
        model.addAttribute("seat", seat);
        model.addAttribute("fromStationName", stationService.getStationInfo(trip.fromStationId()).stationName());
        model.addAttribute("toStationName", stationService.getStationInfo(trip.toStationId()).stationName());

        if (booking.status() != BookingStatus.CANCELED) {
            try {
                model.addAttribute("payment", paymentsService.getPaymentByBooking(bookingId));
            } catch (Exception ignored) {
                // 결제 정보가 없는 경우 결제 관련 UI는 표시하지 않는다.
            }
        }

        return "booking-detail";
    }

    @PostMapping("/{bookingId}/cancel")
    public String cancel(@PathVariable Long bookingId, RedirectAttributes redirectAttributes) {
        try {
            bookingService.cancelBooking(bookingId);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/web/bookings/" + bookingId;
    }
}
