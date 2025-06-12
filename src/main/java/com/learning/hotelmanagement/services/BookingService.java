package com.learning.hotelmanagement.services;

import com.learning.hotelmanagement.payloads.BookingDto;

import java.util.concurrent.CompletableFuture;

public interface BookingService {

    public CompletableFuture<BookingDto> createBooking(BookingDto bookingDto, Integer guestId, Integer roomId);
}
