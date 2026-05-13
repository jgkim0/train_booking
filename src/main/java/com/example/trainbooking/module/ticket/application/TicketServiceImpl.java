package com.example.trainbooking.module.ticket.application;

import com.example.trainbooking.module.booking.domain.Booking;
import com.example.trainbooking.module.booking.infrastructure.BookingRepository;
import com.example.trainbooking.module.seat.domain.Seat;
import com.example.trainbooking.module.seat.domain.SeatRepository;
import com.example.trainbooking.module.ticket.domain.Ticket;
import com.example.trainbooking.module.ticket.domain.TicketRepository;
import com.example.trainbooking.module.ticket.presentation.dto.TicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;

    @Override
    @Transactional
    public void createTicket(TicketRequest ticketRequest) {
        Booking booked = bookingRepository.getReferenceById(ticketRequest.booking());
        Seat seat = seatRepository.getReferenceById(ticketRequest.seatId());

        Ticket newTicket = Ticket.builder()
                .booking(booked)
                .seat(seat)
                .ticketNo(generateTicketNo())
                .build();

        ticketRepository.save(newTicket);
    }

    // 실제 서비스라면 채번 테이블이나 UUID 기반으로 생성해야 함
    private Long generateTicketNo() {
        return ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L);
    }
}
