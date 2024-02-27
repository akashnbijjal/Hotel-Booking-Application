package com.hotel.booking.service;

import java.util.List;

import com.hotel.booking.model.Booking;

public interface BookingService {

	Booking addbooking(Booking booking);
	
	List<Booking> allbookings();
	
	Booking getbookingbyid(long bookingId);
	
	Booking getbookingbyuserid(long userId);
	
	String deletebyid(long bookingId);
	
}
