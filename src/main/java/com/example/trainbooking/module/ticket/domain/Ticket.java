package com.example.trainbooking.module.ticket.domain;

import com.example.trainbooking.common.BaseEntity;
import com.example.trainbooking.module.booking.domain.Booking;
import com.example.trainbooking.module.seat.domain.Seat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Column(name = "ticket_no")
    private Long ticketNo;

    @Builder
    public Ticket(Booking booking, Seat seat, Long ticketNo) {
        this.booking = booking;
        this.seat = seat;
        this.ticketNo = ticketNo;
    }
}
