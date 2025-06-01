package com.learning.hotelmanagement.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.hotelmanagement.payloads.ApiResponse;
import com.learning.hotelmanagement.payloads.HotelDto;
import com.learning.hotelmanagement.services.HotelService;
import com.learning.hotelmanagement.services.ValidationGroup.CreateGroup;
import com.learning.hotelmanagement.services.ValidationGroup.UpdateGroup;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@PostMapping("/createHotel")
	public ResponseEntity<?> createHotel(@Validated(CreateGroup.class) @RequestBody HotelDto hotelDto) {
//		try {
//			Map<String, String> fieldMap = Map.of("Hotel name", hotelDto.getHotelName(), "Hotel address",
//					hotelDto.getHotelAddress(), "Hotel description", hotelDto.getHotelDescription());
//
//			for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
//				if (isInvalidInput(entry.getValue())) {
//					return new ResponseEntity<>(entry.getKey() + " can't be empty, null, only spaces, or 'string'",
//							HttpStatus.BAD_REQUEST);
//				}
//			}
//		} catch (NullPointerException e) {
//			return new ResponseEntity<>("Data Can't be Null", HttpStatus.BAD_REQUEST);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		HotelDto createdHotelDto = this.hotelService.createHotel(hotelDto);
		return new ResponseEntity<>(createdHotelDto, HttpStatus.CREATED);
	}

//	private boolean isInvalidInput(String input) {
//		return input == null || input.trim().isEmpty() || "string".equalsIgnoreCase(input.trim());
//	}

	@PutMapping("/updateHotel/{hotelId}")
	public ResponseEntity<?> updateHotel(@RequestBody HotelDto hotelDto,
			@PathVariable Integer hotelId) {
		if (allFieldsNullOrBlank(hotelDto)) {
			return new ResponseEntity<>("At least one field must be provided for update.", HttpStatus.BAD_REQUEST);
		}

		HotelDto createdHotelDto = this.hotelService.updateHotel(hotelDto, hotelId);
		return new ResponseEntity<>(createdHotelDto, HttpStatus.CREATED);
	}

	private boolean allFieldsNullOrBlank(HotelDto dto) {
		return (dto.getHotelName() == null || dto.getHotelName().trim().isEmpty())
				&& (dto.getHotelAddress() == null || dto.getHotelAddress().trim().isEmpty())
				&& (dto.getHotelDescription() == null || dto.getHotelDescription().trim().isEmpty());
	}

	@GetMapping("/getHotelById/{hotelId}")
	public ResponseEntity<HotelDto> getHotelById(@PathVariable Integer hotelId) {
		HotelDto hotelById = this.hotelService.getHotelById(hotelId);
		return new ResponseEntity<>(hotelById, HttpStatus.OK);
	}

	@DeleteMapping("/deleteHotelById/{hotelId}")
	public ResponseEntity<ApiResponse> deleteById(@PathVariable Integer hotelId) {
		this.hotelService.deleteHotel(hotelId);
		return new ResponseEntity<>(new ApiResponse("Hotel Deleted Successfully", true), HttpStatus.OK);
	}

	@GetMapping("/getAllHotel")
	public ResponseEntity<List<HotelDto>> getAllUsers() {
		List<HotelDto> allHotels = this.hotelService.getAllHotel();
		return new ResponseEntity<>(allHotels, HttpStatus.OK);
	}
}