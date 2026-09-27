package com.example.trainbooking.test.trip;

import com.example.trainbooking.module.trip.presentation.TripController;
import com.example.trainbooking.module.trip.application.TripService;
import com.example.trainbooking.module.trip.presentation.dto.TripResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TripController.class)
class TripControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TripService tripService;

    @Test
    void trip_조회_API_정상() throws Exception {
        TripResponse trip = new TripResponse(1L, 101L, 10L, 20L,
                LocalDateTime.now(), LocalDateTime.now().plusHours(2));

        when(tripService.getTrip(1L)).thenReturn(trip);

        mockMvc.perform(get("/trips/1"))
                .andExpect(status().isOk());
    }
}
