package com.example.trainbooking.module.payment.presentation;

import com.example.trainbooking.module.payment.application.PaymentsService;
import com.example.trainbooking.module.payment.presentation.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentsService paymentsService;

    @PostMapping("/bookings/{bookingId}/payments")
    public PaymentResponse createPayment(@PathVariable Long bookingId) {
        return paymentsService.createPayment(bookingId);
    }


    @GetMapping("/payments/{paymentId}")
    public PaymentResponse getPaymentInfo(@PathVariable Long paymentId) {
        return paymentsService.getPaymentInfo(paymentId);
    }

    @PatchMapping("/payments/{paymentId}")
    public ResponseEntity<Void> approvePayment(@PathVariable Long paymentId) {

        paymentsService.approvePayment(paymentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
