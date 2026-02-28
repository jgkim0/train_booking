package com.example.trainbooking.domain.seat;


import com.example.trainbooking.domain.trip.domain.Trip;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seat_id")
    private Long seatId;

    @ManyToOne
    @JoinColumn(name="trip_id")
    private Trip trip;

    @Column(name="seat_no")
    private Long seatNo;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private SeatStatus status;
}
