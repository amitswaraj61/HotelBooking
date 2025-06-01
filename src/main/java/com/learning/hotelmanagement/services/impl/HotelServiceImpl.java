package com.learning.hotelmanagement.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.hotelmanagement.entities.Hotel;
import com.learning.hotelmanagement.exceptions.ResourceNotFoundException;
import com.learning.hotelmanagement.payloads.HotelDto;
import com.learning.hotelmanagement.repositories.HotelRepo;
import com.learning.hotelmanagement.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepo hotelRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public HotelDto createHotel(HotelDto hotelDto) {
		Hotel hotel = this.modelMapper.map(hotelDto, Hotel.class);
		hotel.setHotelName(hotelDto.getHotelName().trim());
		hotel.setHotelAddress(hotelDto.getHotelAddress().trim());
		hotel.setHotelDescription(hotelDto.getHotelDescription().trim());
		Hotel saveHotel = this.hotelRepo.save(hotel);
		return this.modelMapper.map(saveHotel, HotelDto.class);
	}

	@Override
	public HotelDto updateHotel(HotelDto hotelDto, Integer hotelId) {
		Hotel hotel = this.hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel id not exists.. " + hotelId));
//		hotel.setHotelName(hotelDto.getHotelName());
//		hotel.setHotelAddress(hotelDto.getHotelAddress());
//		hotel.setHotelDescription(hotelDto.getHotelDescription());
		if (hotelDto.getHotelName() != null && !hotelDto.getHotelName().trim().isEmpty()) {
	        hotel.setHotelName(hotelDto.getHotelName().trim());
	    }

	    if (hotelDto.getHotelAddress() != null && !hotelDto.getHotelAddress().trim().isEmpty()) {
	        hotel.setHotelAddress(hotelDto.getHotelAddress().trim());
	    }

	    if (hotelDto.getHotelDescription() != null && !hotelDto.getHotelDescription().trim().isEmpty()) {
	        hotel.setHotelDescription(hotelDto.getHotelDescription().trim());
	    }

		Hotel updateHotel = this.hotelRepo.save(hotel);
		return this.modelMapper.map(updateHotel, HotelDto.class);
	}

	@Override
	public HotelDto getHotelById(Integer hotelId) {
		Hotel hotel = this.hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel id not exists.. " + hotelId));
		return this.modelMapper.map(hotel, HotelDto.class);

	}

	@Override
	public List<HotelDto> getAllHotel() {
		List<Hotel> hotel = this.hotelRepo.findAll();
		List<HotelDto> hotelDtos = hotel.stream().map(hotels -> this.modelMapper.map(hotels, HotelDto.class))
				.collect(Collectors.toList());
		return hotelDtos;
	}

	@Override
	public void deleteHotel(Integer hotelId) {
		this.hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel id not exists.. " + hotelId));
		this.hotelRepo.deleteById(hotelId);

	}
}