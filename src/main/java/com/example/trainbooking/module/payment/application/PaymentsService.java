package com.example.trainbooking.module.payment.application;

import com.example.trainbooking.module.payment.presentation.dto.PaymentResponse;

public interface PaymentsService {

    void createPayment(Long tirpId);

    void approvePayment(Long paymentId);

    PaymentResponse getPaymentInfo(Long paymentId);

    void cancelPayment(Long bookingId);

    void cancelPaymentByBooking(Long bookingId);
}
