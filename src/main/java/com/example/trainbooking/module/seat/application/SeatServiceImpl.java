package com.example.trainbooking.module.seat.application;

import com.example.trainbooking.common.exception.SeatNotFoundException;
import com.example.trainbooking.module.seat.domain.Seat;
import com.example.trainbooking.module.seat.domain.SeatRepository;
import com.example.trainbooking.module.seat.domain.SeatStatus;
import com.example.trainbooking.module.seat.presentation.dto.SeatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SeatResponse> findAvailableSeats(Long tripId) {
        List<Seat> seatList = seatRepository.findByTrip_TripIdAndStatus(tripId, SeatStatus.AVAILABLE);

        return seatList.stream().map(SeatResponse::from).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SeatResponse findTripSeatInfo(Long seatId) {
        Seat seatInfo = seatRepository.findById(seatId).orElseThrow(()-> new SeatNotFoundException("존재하지 않는 좌석번호입니다."));

        return SeatResponse.from(seatInfo);
    }

    @Override
    @Transactional
    public SeatResponse canceledBookingSeat(Long seatId) {
        Seat seatInfo = seatRepository.findById(seatId).orElseThrow(()-> new SeatNotFoundException("존재하지 않는 좌석번호입니다."));

        seatInfo.cancel();

        return SeatResponse.from(seatInfo);
    }


}
