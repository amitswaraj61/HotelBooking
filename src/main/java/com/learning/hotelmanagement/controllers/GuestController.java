package com.learning.hotelmanagement.controllers;

import com.learning.hotelmanagement.payloads.GuestDto;
import com.learning.hotelmanagement.services.GuestService;
import com.learning.hotelmanagement.services.ValidationGroup;
import com.learning.hotelmanagement.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guest")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping("/addGuest")
    public ResponseEntity<?> createGuest(@Validated(ValidationGroup.CreateGroup.class) @RequestBody GuestDto guestDto) {
        GuestDto guest =  this.guestService.createGuest(guestDto);
        return CommonUtils.createBuildResponse(guest, "Success", HttpStatus.CREATED);
    }

    @PutMapping("/updateGuest/{guestId}")
    public ResponseEntity<?> updateGuest(@Validated(ValidationGroup.UpdateGroup.class) @RequestBody GuestDto guestDto, @PathVariable Integer guestId) {
        GuestDto guest =  this.guestService.updateGuest(guestDto, guestId);
        return CommonUtils.createBuildResponse(guest, "Success", HttpStatus.CREATED);
    }
}
