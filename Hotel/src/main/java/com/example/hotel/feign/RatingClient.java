package com.example.hotel.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.hotel.model.Rating;

@FeignClient(name = "Rating", url = "http://localhost:8082/rating/")
public interface RatingClient {

	@GetMapping("hotel/{hotelId}")
	Rating RatingbyhotelId(@PathVariable("hotelId") long hotelId);
}
