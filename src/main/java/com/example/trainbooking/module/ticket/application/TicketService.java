package com.example.trainbooking.module.ticket.application;

import com.example.trainbooking.module.ticket.presentation.dto.TicketRequest;

public interface TicketService {

    void createTicket(TicketRequest ticketRequest);
}
