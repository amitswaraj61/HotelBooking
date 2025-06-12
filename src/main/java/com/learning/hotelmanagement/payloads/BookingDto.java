package com.learning.hotelmanagement.payloads;

import com.learning.hotelmanagement.entities.Guest;
import com.learning.hotelmanagement.entities.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {

    @Schema(hidden = true)
    private String id;

    @NotNull(message = "Check-in date is required")
    @FutureOrPresent(message = "Check-in date must be today or a future date")
    private LocalDate checkInDate;

    @NotNull(message = "Check-out date is required")
    @Future(message = "Check out date must be future date")
    private LocalDate checkOutDate;

    @Schema(hidden = true)
    private LocalDate bookingDate;

    @Schema(hidden = true)
    private RoomDto room;

    @Schema(hidden = true)
    private GuestDto guest;

}

