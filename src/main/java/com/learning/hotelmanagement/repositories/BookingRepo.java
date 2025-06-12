package com.learning.hotelmanagement.repositories;

import com.learning.hotelmanagement.entities.Booking;
import com.learning.hotelmanagement.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, String> {

}
