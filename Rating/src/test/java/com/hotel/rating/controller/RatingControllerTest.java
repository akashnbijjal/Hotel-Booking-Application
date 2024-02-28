package com.hotel.rating.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hotel.rating.model.Rating;
import com.hotel.rating.service.RatingServiceImpl;

@SpringBootTest
class RatingControllerTest {

	@MockBean
	private RatingServiceImpl service;

	@Autowired
	private RatingController controller;

	@Test
	void test_addrate() {
		fail("Not yet implemented");
	}

	@Test
	void test_getallRating() {
		List<Rating> expectedRatings = Arrays.asList(new Rating(1, 1, 1, 5, "Overall good in food and hygienic"),
				new Rating(2, 2, 2, 4, "Average service"));
		Mockito.when(service.allRate()).thenReturn(expectedRatings);
		ResponseEntity<List<Rating>> response = controller.getallRating();
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(expectedRatings, response.getBody());
	}

	@Test
	void test_Ratingbyuserid() {
		long userId = 1;
		Rating expectedRating = new Rating(1, 1, 1, 5, "Overall good in food and hygienic");
		Mockito.when(service.getRatingbyuserid(userId)).thenReturn(expectedRating);
		ResponseEntity<Rating> response = controller.Ratingbyuserid(userId);
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(expectedRating, response.getBody());
	}

	@Test
	void test_RatingbyhotelId() {
		long hotelId = 1;
		List<Rating> expectedRatings = Arrays.asList(new Rating(1, hotelId, 1, 5, "Overall good in food and hygienic"),
				new Rating(2, hotelId, 2, 4, "Average service"));
		Mockito.when(service.getRatingByHotelId(hotelId)).thenReturn(expectedRatings);
		ResponseEntity<List<Rating>> response = controller.RatingbyhotelId(hotelId);
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(expectedRatings, response.getBody());
	}

	@Test
	void test_getRatingid() {
		long ratingId = 1;
		Rating expectedRating = new Rating(1, 1, 1, 5, "Overall good in food and hygienic");
		Mockito.when(service.getrate(ratingId)).thenReturn(expectedRating);
		ResponseEntity<Rating> response = controller.getRatingid(ratingId);
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(expectedRating, response.getBody());
	}

}
