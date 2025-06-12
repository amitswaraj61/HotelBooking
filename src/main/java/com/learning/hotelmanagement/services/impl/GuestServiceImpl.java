package com.learning.hotelmanagement.services.impl;

import com.learning.hotelmanagement.entities.Guest;
import com.learning.hotelmanagement.entities.Hotel;
import com.learning.hotelmanagement.exceptions.ResourceNotFoundException;
import com.learning.hotelmanagement.payloads.GuestDto;
import com.learning.hotelmanagement.repositories.GuestRepo;
import com.learning.hotelmanagement.services.GuestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepo guestRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GuestDto createGuest(GuestDto guestDto) {
        Guest guest = this.modelMapper.map(guestDto, Guest.class);
        Guest saveGuest = this.guestRepo.save(guest);
        return this.modelMapper.map(saveGuest, GuestDto.class);
    }

    @Override
    public GuestDto updateGuest(GuestDto guestDto, int guestId) {
        Guest guest = this.guestRepo.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Guest id not exists.. " + guestId));

        if (guestDto.getFullName() != null && !guest.getFullName().trim().isEmpty()) {
            guest.setFullName(guestDto.getFullName().trim());
        }

        if (guestDto.getMobileNumber() != null && !guestDto.getMobileNumber().trim().isEmpty()) {
            guest.setMobileNumber(guestDto.getMobileNumber().trim());
        }

        if (guestDto.getEmail() != null && !guestDto.getEmail().trim().isEmpty()) {
            throw new ResourceNotFoundException("Email id cannot change " + guestId);
        }

        if (guestDto.getIdentificationNumber() != null && !guestDto.getIdentificationNumber().trim().isEmpty()) {
            throw new ResourceNotFoundException("IdentificationNumber id cannot change " + guestId);
        }

        Guest updateGuest = this.guestRepo.save(guest);
        return this.modelMapper.map(updateGuest, GuestDto.class);
    }

    @Override
    public GuestDto getGuestById(int guestId) {
        Guest guest = this.guestRepo.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Guest id not exists.. " + guestId));
        return this.modelMapper.map(guest, GuestDto.class);
    }
}
