package com.hotel.rating.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotel.rating.model.Hotel;

@FeignClient(name = "Hotel", url = "http://localhost:8081/hotel/")
public interface HotelFeign {
	@PutMapping("/update/{hotelid}")
	public ResponseEntity<Hotel> updatehotel(@PathVariable("hotelid") long hotelid, @RequestBody Hotel hotel);

	@GetMapping("/id/{hotelid}")
	public ResponseEntity<Hotel> hotelbyid(@PathVariable("hotelid") long hotelid);
}
