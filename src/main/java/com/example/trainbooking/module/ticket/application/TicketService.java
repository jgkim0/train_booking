package com.example.trainbooking.module.ticket.application;

import com.example.trainbooking.module.ticket.presentation.dto.TicketRequest;
import jakarta.validation.Valid;

public interface TicketService {

    void createTicket(TicketRequest ticketRequest);
}
