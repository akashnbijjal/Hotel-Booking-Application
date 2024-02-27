package com.hotel.rating.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.hotel.rating.feign.HotelFeign;
import com.hotel.rating.model.Hotel;
import com.hotel.rating.model.Rating;
import com.hotel.rating.repository.Ratingrepo;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private Ratingrepo repo;

	@Autowired
	private HotelFeign hotelFeign;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Override
	public Rating addrate(Rating rating) {
		long hotelId = rating.getHotelId();
		int newRating = rating.getRating();
		ResponseEntity<Hotel> response = hotelFeign.hotelbyid(hotelId);
		if (response != null && response.getStatusCode() == HttpStatus.ACCEPTED) {

			Hotel updatedhotel = response.getBody();
			updatedhotel.setRating(newRating);
			rating.setRatingId(sequenceGeneratorService.generateSequence(Rating.SEQUENCE_NAME));
			return repo.save(rating);
		} else {
			return null;
		}

	}

	@Override
	public List<Rating> allRate() {
		return repo.findAll();
	}

	@Override
	public Rating getrate(long ratingId) {
		return repo.findById(ratingId).get();
	}

	@Override
	public Rating getRatingbyuserid(long userId) {
		return repo.findByUserId(userId);
	}

	@Override
	public Rating getRatingByHotelId(long hotelId) {

		return repo.findByHotelId(hotelId);
	}

}
