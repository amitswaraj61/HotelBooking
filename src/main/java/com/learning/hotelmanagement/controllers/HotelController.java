package com.learning.hotelmanagement.controllers;

import java.util.List;

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

import com.learning.hotelmanagement.payloads.HotelDto;
import com.learning.hotelmanagement.services.HotelService;
import com.learning.hotelmanagement.services.ValidationGroup.CreateGroup;
import com.learning.hotelmanagement.services.ValidationGroup.UpdateGroup;
import com.learning.hotelmanagement.utils.CommonUtils;
import com.learning.hotelmanagement.utils.ErrorResponse;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@PostMapping("/createHotel")
	public ResponseEntity<?> createHotel(@Validated(CreateGroup.class) @RequestBody HotelDto hotelDto) {
		HotelDto createdHotelDto = this.hotelService.createHotel(hotelDto);
		return CommonUtils.createBuildResponse(createdHotelDto, "Success", HttpStatus.CREATED);
	}

	@PutMapping("/updateHotel/{hotelId}")
	public ResponseEntity<?> updateHotel(@Validated(UpdateGroup.class) @RequestBody HotelDto hotelDto,
			@PathVariable Integer hotelId) {
		if (allFieldsNullOrBlank(hotelDto)) {
			String errorMessage = "At least one field must be provided for update.";
			ErrorResponse error = new ErrorResponse(errorMessage, false, HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		HotelDto updatedHotelDto = this.hotelService.updateHotel(hotelDto, hotelId);
		return CommonUtils.createBuildResponse(updatedHotelDto, "Success", HttpStatus.CREATED);
	}

	private boolean allFieldsNullOrBlank(HotelDto dto) {
		return (dto.getHotelName() == null || dto.getHotelName().trim().isEmpty())
				&& (dto.getHotelAddress() == null || dto.getHotelAddress().trim().isEmpty())
				&& (dto.getHotelDescription() == null || dto.getHotelDescription().trim().isEmpty());
	}

	@GetMapping("/getHotelById/{hotelId}")
	public ResponseEntity<?> getHotelById(@PathVariable Integer hotelId) {
		HotelDto hotelById = this.hotelService.getHotelById(hotelId);
		return CommonUtils.createBuildResponse(hotelById, "Success", HttpStatus.OK);
	}

	@DeleteMapping("/deleteHotelById/{hotelId}")
	public ResponseEntity<?> deleteById(@PathVariable Integer hotelId) {
		this.hotelService.deleteHotel(hotelId);
		return CommonUtils.createBuildResponse("Hotel Deleted Successfully", "Success", HttpStatus.OK);
	}

	@GetMapping("/getAllHotel")
	public ResponseEntity<?> getAllUsers() {
		List<HotelDto> allHotels = this.hotelService.getAllHotel();
		return CommonUtils.createBuildResponse(allHotels, "Success", HttpStatus.OK);
	}
}