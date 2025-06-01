package com.learning.hotelmanagement.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.learning.hotelmanagement.entities.Hotel;
import com.learning.hotelmanagement.entities.Room;
import com.learning.hotelmanagement.exceptions.ResourceNotFoundException;
import com.learning.hotelmanagement.payloads.HotelDto;
import com.learning.hotelmanagement.payloads.RoomDto;
import com.learning.hotelmanagement.repositories.HotelRepo;
import com.learning.hotelmanagement.repositories.RoomRepo;
import com.learning.hotelmanagement.services.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private HotelRepo hotelRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoomRepo roomRepo;

	@Override
	public RoomDto addRoom(RoomDto roomDto, Integer hotelId) {
		Hotel hotel = this.hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel id not exists.. " + hotelId));
		Room room = this.modelMapper.map(roomDto, Room.class);
		room.setHotel(hotel);
		Room savedRoom = this.roomRepo.save(room);
		RoomDto responseDto = modelMapper.map(savedRoom, RoomDto.class);
		//responseDto.setHotelDto(modelMapper.map(hotel, HotelDto.class));
		return responseDto;
	}

	@Override
	public RoomDto updateRoom(RoomDto roomDto, Integer roomId) {
		Room room = this.roomRepo.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room id not exists.. " + roomId));
		room.setPrice(roomDto.getPrice());
		room.setRoomDescription(roomDto.getRoomDescription());
		room.setStatus(roomDto.getStatus());
		room.setRoomNumber(room.getRoomNumber());
		room.setRoomType(roomDto.getRoomType());
		Room updateRoom = this.roomRepo.save(room);
		return this.modelMapper.map(updateRoom, RoomDto.class);
	}

	@Override
	public RoomDto getRoomById(Integer roomId) {
		Room room = this.roomRepo.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room id not exists.. " + roomId));
		return this.modelMapper.map(room, RoomDto.class);
	}

	@Override
	public List<RoomDto> getAllRoom() {
		List<Room> room = this.roomRepo.findAll();
		List<RoomDto> roomDtos = room.stream().map(allRooms -> this.modelMapper.map(allRooms, RoomDto.class))
				.collect(Collectors.toList());
		return roomDtos;
	}

	@Override
	public void deleteRoom(Integer roomId) {
		this.roomRepo.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room id not exists.. " + roomId));
		this.roomRepo.deleteById(roomId);
	}

	@Override
	public List<RoomDto> getRoomByHotelId(Integer hotelId) {
		Hotel hotel = this.hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel id not exists.. " + hotelId));
		List<Room> room = this.roomRepo.findByHotel(hotel);
		return room.stream().map((roomId) -> this.modelMapper.map(roomId, RoomDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<RoomDto> searchRoom(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
}