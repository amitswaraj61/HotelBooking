package com.learning.hotelmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.hotelmanagement.entities.Hotel;
import com.learning.hotelmanagement.entities.Room;

public interface RoomRepo extends JpaRepository<Room, Integer> {
	
	List<Room> findByHotel(Hotel hotel);

}
