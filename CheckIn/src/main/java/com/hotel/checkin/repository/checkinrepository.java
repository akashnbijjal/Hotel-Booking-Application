package com.hotel.checkin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hotel.checkin.model.CheckInrecord;

public interface checkinrepository extends MongoRepository<CheckInrecord, Long>{

	
	CheckInrecord findByUserId(long userId);
	
}
