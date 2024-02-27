package com.hotel.booking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hotel.booking.model.Booking;

public interface bookingrepository extends MongoRepository<Booking, Long>{

	Booking findByUserId(long userId);
	
	Boolean existsByUserId(long userId);
	
}
