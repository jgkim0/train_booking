package com.example.trainbooking.common;

import com.example.trainbooking.common.exception.BookingNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String> handleNoBookingException(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("e.getMessage()");

    }
}
