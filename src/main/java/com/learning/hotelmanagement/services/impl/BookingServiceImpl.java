package com.learning.hotelmanagement.services.impl;

import com.learning.hotelmanagement.entities.Booking;
import com.learning.hotelmanagement.entities.Guest;
import com.learning.hotelmanagement.entities.Room;
import com.learning.hotelmanagement.entities.RoomStatus;
import com.learning.hotelmanagement.exceptions.ResourceNotFoundException;
import com.learning.hotelmanagement.payloads.BookingDto;
import com.learning.hotelmanagement.repositories.BookingRepo;
import com.learning.hotelmanagement.repositories.GuestRepo;
import com.learning.hotelmanagement.repositories.RoomRepo;
import com.learning.hotelmanagement.services.BookingService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Future;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private GuestRepo guestRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookingRepo bookingRepo;

    @Override
    @Async("asyncExecutor")
    @Transactional
    public CompletableFuture<BookingDto> createBooking(BookingDto bookingDto, Integer guestId, Integer roomId) {
        System.out.println(Thread.currentThread().getName() + "<-----");
        Guest guest = this.guestRepo.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Guest id not exists.. " + guestId));
        Room room = this.roomRepo.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room id not exists.. " + roomId));

        if (room.getStatus() != RoomStatus.AVAILABLE) {
         throw new ResourceNotFoundException("Room is not available");
        }

        Booking booking = this.modelMapper.map(bookingDto, Booking.class);

        booking.setRoom(room);
        booking.setGuest(guest);
        booking.setId(UUID.randomUUID().toString());
        booking.setCheckInDate(bookingDto.getCheckInDate());
        booking.setCheckOutDate(booking.getCheckOutDate());
        booking.setBookingDate(LocalDate.now());

        room.setStatus(RoomStatus.BOOKED);

        try {
            roomRepo.save(room);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Room is not getting save");
        }

        try {
            bookingRepo.save(booking);
        } catch (Exception e) {
            throw new ResourceNotFoundException("booking failed");
        }


        return CompletableFuture.completedFuture(this.modelMapper.map(booking, BookingDto.class));

    }
}
