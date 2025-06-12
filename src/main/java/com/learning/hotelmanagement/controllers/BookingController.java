package com.learning.hotelmanagement.controllers;

import com.learning.hotelmanagement.entities.Guest;
import com.learning.hotelmanagement.payloads.BookingDto;
import com.learning.hotelmanagement.services.BookingService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController()
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/{guestId}/{roomId}")
    public CompletableFuture<ResponseEntity<?>> createBooking(
            @Valid @RequestBody BookingDto bookingDto,
            @PathVariable Integer guestId,
            @PathVariable Integer roomId) {
        return bookingService.createBooking(bookingDto, guestId, roomId).thenApply(ResponseEntity:: ok);
    }
}
