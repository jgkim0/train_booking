package com.example.trainbooking.test.trip;

import com.example.trainbooking.domain.trip.domain.Trip;
import com.example.trainbooking.domain.trip.domain.TripRepository;
import com.example.trainbooking.domain.trip.application.TripServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class TripServiceTest {

    @Mock
    TripRepository tripRepository;

    @InjectMocks
    TripServiceImpl tripService;

    @Test
    void trip이_존재하면_정상반환() {
        //given
        Long id = 1L;
        Trip trip = new Trip();
        given(tripRepository.findById(id))
                .willReturn(Optional.of(trip));

        //when
        Trip result = tripService.getTrip(id);

        //then
        assertThat(result).isEqualTo(trip);

    }

    @Test
    void trip이_없으면_예외_발생() {
        //given
        Long id = 99L;
        given(tripRepository.findById(id))
                .willReturn(Optional.empty());


        //when
        //then
        assertThatThrownBy(()->tripService.getTrip(id))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
