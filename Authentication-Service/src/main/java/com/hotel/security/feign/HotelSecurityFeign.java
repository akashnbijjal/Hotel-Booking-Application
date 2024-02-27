package com.hotel.security.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "Hotel",url = "http://localhost:8081/hotel/")
public interface HotelSecurityFeign {

	@GetMapping("/allhotel")
	public ResponseEntity<List<Hotel>> getAll();
	
}
