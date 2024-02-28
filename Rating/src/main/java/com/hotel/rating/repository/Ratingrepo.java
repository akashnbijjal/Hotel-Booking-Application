package com.hotel.rating.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hotel.rating.model.Rating;

public interface Ratingrepo extends MongoRepository<Rating, Long> {

	Rating findByUserId(long userId);


	boolean existsByUserId(Object object);

	boolean existsByHotelId(long hotelId);

	List<Rating> findAllByHotelId(long hotelId);
}
