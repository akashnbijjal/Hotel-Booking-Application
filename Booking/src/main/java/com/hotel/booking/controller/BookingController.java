package com.hotel.booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Booking> addbooking(@RequestBody Booking booking) {
		Booking newBooking = service.addbooking(booking);
		logger.info("booking details added successfully");
		return new ResponseEntity<Booking>(newBooking, HttpStatus.CREATED);

	}

	@Operation(summary = "Get booking details by bookingId", description = "Endpoint to get booking", operationId = "getBooking")
	@GetMapping("booking/{bookingId}")
	public ResponseEntity<Booking> bookingbyid(@PathVariable("bookingId") long bookingId) {
		Booking booking = service.getbookingbyid(bookingId);
		logger.info("Getting booking details based on booking id");
		return new ResponseEntity<>(booking, HttpStatus.ACCEPTED);
	}

	@Operation(summary = "Get all bookings", description = "Endpoint to get all bookings", operationId = "getBookings")
	@GetMapping("allbookings")
	public ResponseEntity<List<Booking>> allbookings() {
		List<Booking> bookings = service.allbookings();
		logger.info("Getting all booking details");
		return new ResponseEntity<>(bookings, HttpStatus.ACCEPTED);
	}

	@Operation(summary = "Get booking details by user", description = "Endpoint to get bookings by user id", operationId = "getBookingsByUser")
	@GetMapping("user/{userId}")
	public ResponseEntity<Booking> bookingbyuserid(@PathVariable("userId") long userId) {
		Booking booking = service.getbookingbyuserid(userId);
		logger.info("Getting booking detail based on user id");
		return new ResponseEntity<>(booking, HttpStatus.ACCEPTED);
	}

	@Operation(summary = "Delete booking", description = "Endpoint to delete booking by booking id", operationId = "deleteBooking")
	@DeleteMapping("delete/{bookingId}")
	public ResponseEntity<String> deletebyid(@PathVariable("bookingId") long bookingId) {
		String message = service.deletebyid(bookingId);
		logger.info("Booking deleted successfully with ID: " + bookingId);
		return new ResponseEntity<>(message, HttpStatus.ACCEPTED);

	}

}
