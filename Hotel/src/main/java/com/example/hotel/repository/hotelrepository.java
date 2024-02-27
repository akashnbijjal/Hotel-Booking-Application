package com.example.hotel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.hotel.model.Hotel;

public interface hotelrepository extends MongoRepository<Hotel, Long>{

	Hotel findByLocationIgnoreCaseContaining(String location);

	boolean existsByName(String name);
	
	
}
