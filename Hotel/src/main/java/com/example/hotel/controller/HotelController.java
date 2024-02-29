package com.example.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.model.Hotel;
import com.example.hotel.service.HotelServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("hotel")
public class HotelController {

	@Autowired
	private HotelServiceImpl service;

	@Operation(summary = "Add a new hotel", description = "Endpoint to add a new hotel", operationId = "addHotel")
	@PostMapping("/addhotel")
	public ResponseEntity<Hotel> createhotel(@RequestBody Hotel hotel) {
		Hotel h = service.create(hotel);
		return new ResponseEntity<Hotel>(h, HttpStatus.CREATED);
	}

	@Operation(summary = "Get all hotels", description = "Endpoint to get all hotels", operationId = "gethotels")
	@GetMapping("/allhotel")
	public ResponseEntity<List<Hotel>> getAll() {
		List<Hotel> hotel = service.getAll();
		return new ResponseEntity<List<Hotel>>(hotel, HttpStatus.ACCEPTED);
	}

	@Operation(summary = "Get hotel by hotelid", description = "Endpoint to get hotel by hotelid", operationId = "gethotel")
	@GetMapping("/id/{hotelid}")
	public ResponseEntity<Hotel> hotelbyid(@PathVariable("hotelid") long hotelid) {
		Hotel hotel = service.getById(hotelid);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.ACCEPTED);
	}

	@Operation(summary = "get hotel by location", description = "Endpoint to get location by hotel", operationId = "getlocation")
	@GetMapping("/location/{location}")
	public ResponseEntity<Hotel> getbylocation(@PathVariable("location") String location) {
		Hotel hotel = service.getByLocation(location);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.ACCEPTED);
	}

	@Operation(summary = "Updating the hotel details", description = "Endpoint to update the hotel", operationId = "updatehotel")
	@PutMapping("/update/{hotelid}")
	public ResponseEntity<Hotel> updatehotel(@PathVariable("hotelid") long hotelid, @RequestBody Hotel hotel) {
		Hotel hotel1 = service.updateHotel(hotelid, hotel);
		return new ResponseEntity<Hotel>(hotel1, HttpStatus.ACCEPTED);
	}

}
