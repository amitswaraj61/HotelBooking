package com.learning.hotelmanagement.services;

import com.learning.hotelmanagement.payloads.GuestDto;

public interface GuestService {
    public GuestDto createGuest(GuestDto guestDto);

    public GuestDto updateGuest(GuestDto guestDto, int guestId);

    public GuestDto getGuestById(int guestId);
}
