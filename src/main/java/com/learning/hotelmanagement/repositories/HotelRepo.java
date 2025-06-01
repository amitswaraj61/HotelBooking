package com.learning.hotelmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.hotelmanagement.entities.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, Integer> {

}
