package com.learning.hotelmanagement.services;

import java.util.List;

import com.learning.hotelmanagement.payloads.HotelDto;

public interface HotelService {

	public HotelDto createHotel(HotelDto hotelDto);

	public HotelDto updateHotel(HotelDto hotelDto, Integer hotelId);

	public HotelDto getHotelById(Integer hotelId);

	public List<HotelDto> getAllHotel();

	public void deleteHotel(Integer hotelId);
}
