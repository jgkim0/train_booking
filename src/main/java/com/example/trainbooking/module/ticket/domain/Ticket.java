package com.example.trainbooking.module.ticket.domain;


import com.example.trainbooking.module.booking.domain.Booking;
import com.example.trainbooking.module.seat.domain.Seat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ticket_id")
    private Long ticketId;

//    @Column(name="booking_id")
    @ManyToOne
    @JoinColumn(name="booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name="seat_id")
    private Seat seat;

//    @Column(name="seat_id")
//    private Long seatId;

    @Column(name="ticket_no")
    private Long ticketNo;

    @Column(name="issued_at")
    private Timestamp issuedAt;

    @Builder
    public Ticket(Booking booking, Seat seat, Long ticketNo, Timestamp issuedAt) {
        this.booking = booking;
        this.seat = seat;
        this.ticketNo = ticketNo;
        this.issuedAt = issuedAt;
    }
}
