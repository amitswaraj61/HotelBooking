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

import com.learning.hotelmanagement.payloads.RoomDto;
import com.learning.hotelmanagement.services.RoomService;
import com.learning.hotelmanagement.services.ValidationGroup.CreateGroup;
import com.learning.hotelmanagement.services.ValidationGroup.UpdateGroup;
import com.learning.hotelmanagement.utils.CommonUtils;
import com.learning.hotelmanagement.utils.ErrorResponse;

@RestController
@RequestMapping("api/room")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@PostMapping("/addRoom/{hotelId}")
	public ResponseEntity<?> createHotel(@Validated(CreateGroup.class) @RequestBody RoomDto roomDto,
			@PathVariable Integer hotelId) {
		RoomDto createdRoomDto = this.roomService.addRoom(roomDto, hotelId);
		return CommonUtils.createBuildResponse(createdRoomDto, "Success", HttpStatus.CREATED);
	}

	@PutMapping("/updateRoom/{roomId}")
	public ResponseEntity<?> updateHotel(@Validated(UpdateGroup.class) @RequestBody RoomDto roomDto,
			@PathVariable Integer roomId) {
		if (allFieldsNullOrBlank(roomDto)) {
			String errorMessage = "At least one field must be provided for update.";
			ErrorResponse error = new ErrorResponse(errorMessage, false, HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		RoomDto updatedRoomDto = this.roomService.updateRoom(roomDto, roomId);
		return CommonUtils.createBuildResponse(updatedRoomDto, "Success", HttpStatus.CREATED);
	}

	private boolean allFieldsNullOrBlank(RoomDto dto) {
		return (dto.getRoomNumber() == null || dto.getRoomNumber().trim().isEmpty())
				&& (dto.getRoomDescription() == null || dto.getRoomDescription().trim().isEmpty())
				&& (dto.getStatus() == null) && (dto.getRoomType() == null) && (dto.getPrice() == null);
	}

	@GetMapping("/getRoomById/{roomId}")
	public ResponseEntity<?> getRoomById(@PathVariable Integer roomId) {
		RoomDto roomById = this.roomService.getRoomById(roomId);
		return CommonUtils.createBuildResponse(roomById, "Success", HttpStatus.OK);
	}

	@GetMapping("/getAllRoom")
	public ResponseEntity<?> getRoomById() {
		List<RoomDto> allRoom = this.roomService.getAllRoom();
		return CommonUtils.createBuildResponse(allRoom, "Success", HttpStatus.OK);
	}

	@DeleteMapping("/deleteRoomById/{roomId}")
	public ResponseEntity<?> deleteRoomById(@PathVariable Integer roomId) {
		this.roomService.deleteRoom(roomId);
		return CommonUtils.createBuildResponse("Hotel Deleted Successfully", "Success", HttpStatus.OK);
	}

	@GetMapping("/getRoomByHotelId/{hotelId}")
	public ResponseEntity<?> getRoomByHotelId(@PathVariable Integer hotelId) {
		List<RoomDto> roomByHotelId = this.roomService.getRoomByHotelId(hotelId);
		return CommonUtils.createBuildResponse(roomByHotelId, "Success", HttpStatus.OK);
	}
}