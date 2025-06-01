package com.learning.hotelmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.hotelmanagement.payloads.ApiResponse;
import com.learning.hotelmanagement.payloads.RoomDto;
import com.learning.hotelmanagement.services.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/room")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@PostMapping("/addRoom/{hotelId}")
	public ResponseEntity<RoomDto> createHotel(@Valid @RequestBody RoomDto roomDto, @PathVariable Integer hotelId) {
		RoomDto createdRoomDto = this.roomService.addRoom(roomDto, hotelId);
		return new ResponseEntity<>(createdRoomDto, HttpStatus.CREATED);
	}

	@PutMapping("/updateRoom/{roomId}")
	public ResponseEntity<RoomDto> updateHotel(@Valid @RequestBody RoomDto roomDto, @PathVariable Integer roomId) {
		RoomDto updatedRoomDto = this.roomService.updateRoom(roomDto, roomId);
		return new ResponseEntity<>(updatedRoomDto, HttpStatus.CREATED);
	}

	@GetMapping("/getRoomById/{roomId}")
	public ResponseEntity<RoomDto> getRoomById(@PathVariable Integer roomId) {
		RoomDto roomById = this.roomService.getRoomById(roomId);
		return new ResponseEntity<>(roomById, HttpStatus.OK);
	}

	@GetMapping("/getAllRoom")
	public ResponseEntity<List<RoomDto>> getRoomById() {
		List<RoomDto> allRoom = this.roomService.getAllRoom();
		return new ResponseEntity<>(allRoom, HttpStatus.OK);
	}

	@DeleteMapping("/deleteRoomById/{roomId}")
	public ResponseEntity<ApiResponse> deleteRoomById(@PathVariable Integer roomId) {
		this.roomService.deleteRoom(roomId);
		return new ResponseEntity<>(new ApiResponse("Room Deleted Successfully", true), HttpStatus.OK);
	}

	@GetMapping("/getRoomByHotelId/{hotelId}")
	public ResponseEntity<List<RoomDto>> getRoomByHotelId(@PathVariable Integer hotelId) {
		List<RoomDto> roomByHotelId = this.roomService.getRoomByHotelId(hotelId);
		return new ResponseEntity<>(roomByHotelId, HttpStatus.OK);
	}
}