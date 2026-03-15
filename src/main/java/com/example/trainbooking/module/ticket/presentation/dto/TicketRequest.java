package com.example.trainbooking.module.ticket.presentation.dto;

import lombok.Getter;

@Getter
public class TicketRequest {

    private Long booking;
    private Long seatId;

    public TicketRequest(Long booking, Long seatId) {
        this.booking = booking;
        this.seatId = seatId;
    }
}
