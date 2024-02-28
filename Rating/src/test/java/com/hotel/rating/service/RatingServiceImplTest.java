package com.hotel.rating.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.hotel.rating.exception.RatingNotFoundException;
import com.hotel.rating.feign.HotelFeign;
import com.hotel.rating.model.Hotel;
import com.hotel.rating.model.Rating;
import com.hotel.rating.repository.Ratingrepo;

@SpringBootTest
class RatingServiceImplTest {

	@Autowired
	private RatingServiceImpl service;

	@MockBean
	private Ratingrepo ratingrepo;

	@MockBean
	private HotelFeign hotelFeign;

	@MockBean
	private SequenceGeneratorService sequenceGeneratorService;

	@Test
	void test_addrate() {
		long hotelId = 1;
		int newRating = 5;
		long ratingId = 1;
		Rating rating = new Rating(ratingId, hotelId, 1, newRating, "Overall good in food and hygienic");
		ResponseEntity<Hotel> responseEntity = hotelFeign.hotelbyid(hotelId);
		Hotel expectedHotel = responseEntity.getBody();
		expectedHotel.setRating(newRating);
		Mockito.when(hotelFeign.hotelbyid(hotelId)).thenReturn(responseEntity);
		Mockito.when(ratingrepo.save(rating)).thenReturn(rating);
		Mockito.when(sequenceGeneratorService.generateSequence(Rating.SEQUENCE_NAME)).thenReturn(ratingId);
		Rating savedRating = service.addrate(rating);
		assertEquals(newRating, savedRating.getRating());
	}

	@Test
	void test_getrate_success() {
		long ratingId = 1;
		Rating expectedRating = new Rating(1, 1, 1, 5, "Overall good in food and hygenic");
		Mockito.when(ratingrepo.existsById(ratingId)).thenReturn(true);
		Mockito.when(ratingrepo.findById(ratingId)).thenReturn(Optional.of(expectedRating));
		Rating savedRating = service.getrate(ratingId);
		assertEquals(expectedRating, savedRating);
	}

	@Test
	void test_getrate_failed() {
		long ratingId = -1;
		Mockito.when(ratingrepo.existsById(ratingId)).thenReturn(false);
		RatingNotFoundException exception = assertThrows(RatingNotFoundException.class,
				() -> service.getrate(ratingId));
		assertEquals("Rating not found ID: " + ratingId, exception.getMsg());
	}

	@Test
	void test_getRatingbyuserid_success() {
		long userId = 1;
		Rating expectedRating = new Rating(1, 1, 1, 5, "Overall good in food and hygienic");
		Mockito.when(ratingrepo.existsByUserId(userId)).thenReturn(true);
		Mockito.when(ratingrepo.findByUserId(userId)).thenReturn(expectedRating);
		Rating savedRating = service.getRatingbyuserid(userId);
		assertEquals(expectedRating, savedRating);
	}

	@Test
	void test_getRatingbyuserid_failed() {
		long userId = -1;
		Mockito.when(ratingrepo.existsByUserId(userId)).thenReturn(false);
		RatingNotFoundException exception = assertThrows(RatingNotFoundException.class,
				() -> service.getRatingbyuserid(userId));
		assertEquals("Rating not found with userId: " + userId, exception.getMsg());
	}

	@Test
	void test_getRatingByHotelId_success() {
		long hotelId = 1;
		List<Rating> expectedRatings = Arrays.asList(new Rating(1, 1, 1, 5, "Overall good in food and hygienic"),
				new Rating(2, 2, 2, 4, "Average service"));
		Mockito.when(ratingrepo.existsByHotelId(hotelId)).thenReturn(true);
		Mockito.when(ratingrepo.findAllByHotelId(hotelId)).thenReturn(expectedRatings);
		List<Rating> savedRating = service.getRatingByHotelId(hotelId);
		assertEquals(expectedRatings, savedRating);
	}

	@Test
	void test_getRatingByHotelId_failed() {
		long hotelId = -1;
		Mockito.when(ratingrepo.existsByHotelId(hotelId)).thenReturn(false);
		RatingNotFoundException exception = assertThrows(RatingNotFoundException.class,
				() -> service.getRatingByHotelId(hotelId));
		assertEquals("Rating not found with HotelId: " + hotelId, exception.getMsg());
	}

	@Test
	void test_allRate() {
		List<Rating> expectedRatings = Arrays.asList(new Rating(1, 1, 1, 5, "Overall good in food and hygienic"),
				new Rating(2, 2, 2, 4, "Average service"));

		Mockito.when(ratingrepo.findAll()).thenReturn(expectedRatings);
		List<Rating> allRatings = service.allRate();
		assertEquals(expectedRatings.size(), allRatings.size());
	}

}
