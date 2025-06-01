package com.learning.hotelmanagement.services;

import java.util.List;

import com.learning.hotelmanagement.payloads.RoomDto;

public interface RoomService {

	public RoomDto addRoom(RoomDto roomDto, Integer hotelId);

	public RoomDto updateRoom(RoomDto roomDto, Integer roomId);

	public RoomDto getRoomById(Integer roomId);

	public List<RoomDto> getAllRoom();

	public void deleteRoom(Integer roomId);

	public List<RoomDto> getRoomByHotelId(Integer hotelId);

	public List<RoomDto> searchRoom(String keyword);

}
