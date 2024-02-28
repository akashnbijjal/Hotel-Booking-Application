package com.hotel.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.rating.model.Rating;
import com.hotel.rating.service.RatingServiceImpl;

@RestController
@RequestMapping("rating")
public class RatingController {

	@Autowired
	private RatingServiceImpl service;

	@PostMapping("addrating")
	public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
		Rating r = service.addrate(rating);
		return new ResponseEntity<Rating>(r, HttpStatus.CREATED);

	}

	@GetMapping("allrating")
	public ResponseEntity<List<Rating>> getallRating() {
		List<Rating> list = service.allRate();
		return new ResponseEntity<List<Rating>>(list, HttpStatus.ACCEPTED);

	}

	@GetMapping("{ratingId}")
	public ResponseEntity<Rating> getRatingid(@PathVariable("ratingId") long ratingId) {
		Rating rating = service.getrate(ratingId);
		return new ResponseEntity<Rating>(rating, HttpStatus.ACCEPTED);

	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Rating> Ratingbyuserid(@PathVariable("userId") long userId) {

		Rating rating = service.getRatingbyuserid(userId);
		return new ResponseEntity<Rating>(rating, HttpStatus.ACCEPTED);

	}

	@GetMapping("hotel/{hotelId}")
	public ResponseEntity<List<Rating>> RatingbyhotelId(@PathVariable("hotelId") long hotelId) {
		List<Rating> rating = service.getRatingByHotelId(hotelId);
		return new ResponseEntity<List<Rating>>(rating, HttpStatus.ACCEPTED);

	}

}
