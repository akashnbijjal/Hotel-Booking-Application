package com.hotel.booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.booking.model.Booking;
import com.hotel.booking.service.BookingServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("booking")
public class BookingController {

	@Autowired
	private BookingServiceImpl service;

	Logger logger = LoggerFactory.getLogger(BookingController.class);

	@Operation(summary = "Add a new booking", description = "Endpoint to add a new booking", operationId = "addBooking")
	@PostMapping("addbooking")
	public Booking addbooking(@RequestBody Booking booking) {

		Booking b = service.addbooking(booking);
		logger.info("booking details added successfully");
		return b;
	}

	@Operation(summary = "Get booking details by bookingId", description = "Endpoint to get booking", operationId = "getBooking")
	@GetMapping("{bookingId}")
	public Booking bookingbyid(@PathVariable("bookingId") long bookingId) {
		Booking b = service.getbookingbyid(bookingId);
		logger.info("getting booking details based on bookingid");
		return b;
	}

	@Operation(summary = "Get all bookings", description = "Endpoint to get all bookings", operationId = "getBooking")
	@GetMapping("allbookings")
	public List<Booking> allbookings() {
		List<Booking> list = service.allbookings();
		logger.info("getting all booking details");
		return list;
	}

	@Operation(summary = "Get booking details by user", description = "Endpoint to get bookings by userid", operationId = "getBooking")
	@GetMapping("user/{userId}")
	public Booking bookingbyuserid(@PathVariable("userId") long userId) {
		Booking booking = service.getbookingbyuserid(userId);
		logger.info("getting booking detail based on userid");
		return booking;
	}

	@Operation(summary = "delete booking", description = "Endpoint to delete by bookingid", operationId = "deleteBooking")
	@DeleteMapping("{bookingId}")
	public String deletebyid(@PathVariable("bookingId") long bookingId) {
		logger.info("booking deleted!!!!!!!!!");
		return service.deletebyid(bookingId);
	}

}
