package com.example.trainbooking.module.payment.application;

import com.example.trainbooking.common.exception.BookingNotFoundException;
import com.example.trainbooking.common.exception.DuplicationBookingException;
import com.example.trainbooking.common.exception.PaymentNotFoundException;
import com.example.trainbooking.module.booking.domain.Booking;
import com.example.trainbooking.module.booking.infrastructure.BookingRepository;
import com.example.trainbooking.module.payment.domain.Payment;
import com.example.trainbooking.module.payment.domain.PaymentRepository;
import com.example.trainbooking.module.payment.presentation.dto.PaymentResponse;
import com.example.trainbooking.module.ticket.application.TicketService;
import com.example.trainbooking.module.ticket.presentation.dto.TicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentsServiceImpl implements PaymentsService {

    private final PaymentRepository paymentRepository;

    private final BookingRepository bookingRepository;

    private final TicketService ticketService;

    @Override
    @Transactional
    public PaymentResponse createPayment(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                            .orElseThrow(() -> new BookingNotFoundException("조회된 예약 건이 없습니다."));

        checkAlreadyPaymentLog(bookingId);

        Payment payment = Payment.create(booking, 5800);

        return PaymentResponse.from(paymentRepository.save(payment));
    }

    private void checkAlreadyPaymentLog(Long bookingId) {

        if (paymentRepository.existsByBooking_BookingId(bookingId)) {
            throw new DuplicationBookingException("동일한 예약 건이 있습니다.");
        }
    }

    @Override
    @Transactional
    public void approvePayment(Long paymentId) {

        Payment target = paymentRepository.findById(paymentId).orElseThrow(()->new PaymentNotFoundException("조회된 결제 건이 없습니다."));

        // 결제 승인 상태 변경
        target.approved();

        Booking booking = target.getBooking();

        // 예약 취소 여부 검증
        booking.validateCancelable();

        // 예약 지불 상태 변경
        booking.paid();

        // 티켓 발급
        TicketRequest ticketRequest = new TicketRequest(booking.getBookingId(), booking.getSeat().getSeatId());

        ticketService.createTicket(ticketRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getPaymentInfo(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()-> new PaymentNotFoundException("조회된 결제 건이 없습니다."));
        return PaymentResponse.from(payment);
    }

    @Override
    @Transactional
    public void cancelPayment(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                                        .orElseThrow(()->new PaymentNotFoundException("취소할 결제내역이 없습니다."));
        payment.canceled();
    }

    @Override
    public void cancelPaymentByBooking(Long bookingId) {

        Payment payment = paymentRepository.findByBooking_BookingId(bookingId)
                            .orElseThrow(() -> new PaymentNotFoundException("취소할 결제내역이 없습니다."));

        payment.canceled();

    }

}
