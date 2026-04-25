package com.example.trainbooking.module.seat.domain;


import com.example.trainbooking.module.trip.domain.Trip;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


// @NoArgsConstructor 기본생성자(파라미터가 없는 디폴트 생성자) 자동 생성
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seat_id")
    private Long seatId;

    @Column(name="seat_no")
    private Long seatNo;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private SeatStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="trip_id")
    private Trip trip;

    @Version
    private Long version;

    public Seat(Long seatNo, SeatStatus status, Trip trip) {
        this.seatNo = seatNo;
        this.status = status;
        this.trip = trip;
    }

    public void checkBeforeBook() {
        if (this.status != SeatStatus.AVAILABLE) {
            throw new IllegalStateException("이미 예약된 좌석입니다.");
        }
    }

    public void cancel() {
        this.status = SeatStatus.CANCELLED;
    }

    public void release() {
        this.status = SeatStatus.AVAILABLE;
    }

    public void book() {
        this.status = SeatStatus.BOOKED;
    }
}
