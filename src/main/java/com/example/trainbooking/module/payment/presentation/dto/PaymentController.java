package com.example.trainbooking.module.payment.presentation.dto;

import com.example.trainbooking.module.booking.application.BookingService;
import com.example.trainbooking.module.payment.application.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentsService paymentsService;

    private final BookingService bookingService;

    @PostMapping("/bookings/{bookingId}/payments")
    public void initPayment(@PathVariable Long bookingId) {
        paymentsService.createPayment(bookingId);
    }

    @GetMapping("/payments/{paymentId}")
    public PaymentResponse getPaymentInfo(@PathVariable Long paymentId) {
        return paymentsService.getPaymentInfo(paymentId);
    }

    @PatchMapping("/payments/{paymentId}")
    public void approvePayment(@PathVariable Long paymentId) {
        paymentsService.approvePayment(paymentId);
    }

//    @DeleteMapping("/payments/{paymentId}")
//    public void cancledPayment(@PathVariable Long paymentId) {
//        paymentsService.cancledPayment(paymentId);
//    }


}
