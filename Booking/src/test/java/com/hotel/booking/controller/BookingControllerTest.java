package com.hotel.booking.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hotel.booking.model.Booking;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.service.BookingServiceImpl;

@SpringBootTest
class BookingControllerTest {

	@Autowired
	private BookingController controller;

	@MockBean
	private BookingServiceImpl service;

	@Test
	void test_addbooking() {
		Map<String, Double> roomTypes = new HashMap<>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);
		Booking expectedBooking = new Booking(1, 123, 240, "standard", 2, 1, 101, newHotel);
		when(service.addbooking(expectedBooking)).thenReturn(expectedBooking);
		ResponseEntity<Booking> response = controller.addbooking(expectedBooking);
		assertEquals(expectedBooking, response.getBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void test_bookingbyid() {
		long bookingId = 1;
		Map<String, Double> roomTypes = new HashMap<>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);
		Booking expectedBooking = new Booking(1, 123, 240, "standard", 2, 1, 101, newHotel);
		when(service.getbookingbyid(bookingId)).thenReturn(expectedBooking);
		ResponseEntity<Booking> response = controller.bookingbyid(bookingId);
		assertEquals(expectedBooking, response.getBody());
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	}

	@Test
	void test_allbookings() {
		Map<String, Double> roomTypes = new HashMap<>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);
		Booking expectedBooking = new Booking(1, 123, 240, "standard", 2, 1, 101, newHotel);
		when(service.allbookings()).thenReturn(List.of(expectedBooking));
		ResponseEntity<List<Booking>> response = controller.allbookings();
		assertEquals(List.of(expectedBooking), response.getBody());
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	}

	@Test
	void test_bookingbyuserid() {
		long userId = 101;
		Map<String, Double> roomTypes = new HashMap<>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);
		Booking expectedBooking = new Booking(1, 123, 240, "standard", 2, 1, 101, newHotel);
		when(service.getbookingbyuserid(userId)).thenReturn(expectedBooking);
		ResponseEntity<Booking> response = controller.bookingbyuserid(userId);
		assertEquals(expectedBooking, response.getBody());
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	}

	@Test
	void test_deletebyid() {
		long bookingId = 1;
		String message = "Booking deleted successfully with ID: " + bookingId;
		when(service.deletebyid(bookingId)).thenReturn(message);
		ResponseEntity<String> response = controller.deletebyid(bookingId);
		assertEquals(message, response.getBody());
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	}

}
