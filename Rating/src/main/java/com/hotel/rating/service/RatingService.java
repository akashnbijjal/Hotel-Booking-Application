package com.hotel.rating.service;

import java.util.List;

import com.hotel.rating.model.Rating;

public interface RatingService {

	Rating addrate(Rating rating);
	
	List<Rating> allRate();
	
	Rating getrate(long ratingId);
	
	Rating getRatingbyuserid(long userId);
	
	Rating getRatingByHotelId(long hotelId);
	
}
