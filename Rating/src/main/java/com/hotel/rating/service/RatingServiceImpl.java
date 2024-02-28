package com.hotel.rating.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.rating.exception.RatingNotFoundException;
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
		List<Rating> allRates = getRatingByHotelId(hotelId);
		double totalScore = newRating;
		int totalResponses = 1;
		for (Rating rate : allRates) {
			totalScore += rate.getRating();
			totalResponses++;
		}
		double newAverageRating = totalScore / totalResponses;

		Hotel newHotel = new Hotel();
		newHotel.setRating(newAverageRating);
		ResponseEntity<Hotel> response = hotelFeign.updatehotel(hotelId, newHotel);
		Hotel updatedhotel = response.getBody();
		updatedhotel.setRating(newRating);
		rating.setRatingId(sequenceGeneratorService.generateSequence(Rating.SEQUENCE_NAME));
		return repo.save(rating);
	}

	@Override
	public List<Rating> allRate() {
		return repo.findAll();
	}

	@Override
	public Rating getrate(long ratingId) {
		if (repo.existsById(ratingId)) {
			return repo.findById(ratingId).get();
		} else {
			throw new RatingNotFoundException("Rating not found ID: " + ratingId);
		}
	}

	@Override
	public Rating getRatingbyuserid(long userId) {
		if (repo.existsByUserId(userId)) {
			return repo.findByUserId(userId);
		} else {
			throw new RatingNotFoundException("Rating not found with userId: " + userId);
		}
	}

	@Override
	public List<Rating> getRatingByHotelId(long hotelId) {
		if (repo.existsByHotelId(hotelId)) {
			return repo.findAllByHotelId(hotelId);
		} else {
			throw new RatingNotFoundException("Rating not found with HotelId: " + hotelId);

		}
	}

}
