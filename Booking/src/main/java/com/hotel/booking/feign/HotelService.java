package com.hotel.booking.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.booking.model.Hotel;

@FeignClient(name = "Hotel", url = "http://localhost:8081/hotel/")
public interface HotelService {

	@GetMapping("/id/{hotelid}")
	public ResponseEntity<Hotel> hotelbyid(@PathVariable("hotelid") long hotelid);

}
