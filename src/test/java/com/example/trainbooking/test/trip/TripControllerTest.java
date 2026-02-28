package com.example.trainbooking.test.trip;

import com.example.trainbooking.domain.trip.domain.Trip;
import com.example.trainbooking.domain.trip.presentation.TripController;
import com.example.trainbooking.domain.trip.application.TripService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TripController.class)
@ActiveProfiles("test")
class TripControllerTest {

        @Autowired
        MockMvc mockMvc;

        @Mock
        TripService tripService;

        @Test
        void trip_조회_API_정상() throws Exception {
            Trip trip = new Trip();

            when(tripService.getTrip(1L))
                    .thenReturn(trip);

            mockMvc.perform(get("/trips/1"))
                    .andExpect(status().isOk());
        }
}

