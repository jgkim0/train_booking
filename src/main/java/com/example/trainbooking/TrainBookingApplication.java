package com.example.trainbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TrainBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainBookingApplication.class, args);
    }

}
