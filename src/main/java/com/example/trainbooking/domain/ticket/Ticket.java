package com.example.trainbooking.domain.ticket;


import com.example.trainbooking.domain.booking.domain.Booking;
import jakarta.persistence.*;
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

//    @Column(name="seat_id")

    private Long seatId;

    @Column(name="ticket_no")
    private Long ticketNo;

    @Column(name="issued_at")
    private Timestamp issuedAt;
}
