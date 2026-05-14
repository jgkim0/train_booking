package com.example.trainbooking.module.ticket.presentation.dto;

import jakarta.validation.constraints.NotNull;

public record TicketRequest(

        @NotNull
        Long booking,

        @NotNull
        Long seatId
) {

}
