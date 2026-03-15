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

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TicketRepository ticketRepository;

    private final BookingRepository bookingRepository;

    private final SeatRepository seatRepository;


    @Override
    public void createTicket(TicketRequest ticketRequest) {

        Booking booked = bookingRepository.getReferenceById(ticketRequest.getBooking());

        Seat seat = seatRepository.getReferenceById(ticketRequest.getSeatId());

        Ticket newTicket = Ticket.builder()
                .booking(booked)
                .seat(seat).ticketNo(55L).issuedAt(Timestamp.valueOf(LocalDateTime.now())).build();

        ticketRepository.save(newTicket);
    }
}
