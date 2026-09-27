package com.example.trainbooking.web;

import com.example.trainbooking.module.payment.application.PaymentsService;
import com.example.trainbooking.module.payment.presentation.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/web/payments")
public class PaymentViewController {

    private final PaymentsService paymentsService;

    @PostMapping("/{paymentId}/approve")
    public String approve(@PathVariable Long paymentId, RedirectAttributes redirectAttributes) {
        PaymentResponse payment = paymentsService.getPaymentInfo(paymentId);

        try {
            paymentsService.approvePayment(paymentId);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/web/bookings/" + payment.bookingId();
    }
}
