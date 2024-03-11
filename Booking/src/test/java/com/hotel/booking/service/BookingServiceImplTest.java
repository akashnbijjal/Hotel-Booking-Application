package com.hotel.booking.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hotel.booking.exception.BookingNotFound;
import com.hotel.booking.exception.UserNotFoundException;
import com.hotel.booking.feign.HotelService;
import com.hotel.booking.model.Booking;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.repository.bookingrepository;

@SpringBootTest
class BookingServiceImplTest {

	@Autowired
	private BookingServiceImpl service;

	@MockBean
	private bookingrepository repo;

	@MockBean
	private HotelService feignclient;

	@MockBean
	private SequenceGeneratorService sequenceGeneratorService;

	@Test
	void test_getbookingbyid_success() {
		long bookingId = 1;
		Map<String, Double> roomTypes = new HashMap<>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);

		Booking expectedBooking = new Booking(1, 123, 240, "standard", 2, 1, 101, newHotel);
		ResponseEntity<Hotel> hotelResponse = new ResponseEntity<>(newHotel, HttpStatus.ACCEPTED);

		when(repo.existsById(bookingId)).thenReturn(true);
		when(repo.findById(bookingId)).thenReturn(Optional.of(expectedBooking));
		when(feignclient.hotelbyid(Mockito.anyLong())).thenReturn(hotelResponse);

		Booking actualBooking = service.getbookingbyid(bookingId);

		assertEquals(expectedBooking, actualBooking);
	}

	@Test
	void test_getbookingbyid_bookingNotFound() {
		long bookingId = 1;

		when(repo.existsById(bookingId)).thenReturn(false);

		assertThrows(BookingNotFound.class, () -> service.getbookingbyid(bookingId));
	}

	@Test
	void test_getbookingbyuserid_success() {
		long userId = 1;
		Booking expectedBooking = new Booking(); // Provide necessary details for the expected booking

		when(repo.existsByUserId(userId)).thenReturn(true);
		when(repo.findByUserId(userId)).thenReturn(expectedBooking);

		Booking actualBooking = service.getbookingbyuserid(userId);

		assertEquals(expectedBooking, actualBooking);
	}

	@Test
	void test_getbookingbyuserid_userNotFound() {
		long userId = 1;

		when(repo.existsByUserId(userId)).thenReturn(false);

		assertThrows(UserNotFoundException.class, () -> service.getbookingbyuserid(userId));
	}

	@Test
	void test_deletebyid_success() {
		long bookingId = 1;

		when(repo.existsById(bookingId)).thenReturn(true);

		String result = service.deletebyid(bookingId);

		assertEquals("Deleted successfully with ID :" + bookingId, result);
		Mockito.verify(repo).deleteById(bookingId);
	}

	@Test
	void test_deletebyid_bookingIdNotFound() {
		long bookingId = 1;

		when(repo.existsById(bookingId)).thenReturn(false);

		String result = service.deletebyid(bookingId);

		assertEquals("BookingId not found", result);
		Mockito.verify(repo, Mockito.never()).deleteById(anyLong());
	}

	@Test
	void test_addbooking_success() {
		// Prepare test data
		Map<String, Double> roomTypes = new HashMap<>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);
		Booking expectedBooking = new Booking(1, 123, 240, "standard", 2, 1, 101, newHotel);

		ResponseEntity<Hotel> hotelResponse = new ResponseEntity<>(newHotel, HttpStatus.ACCEPTED);
		when(feignclient.hotelbyid(newHotel.getHotelId())).thenReturn(hotelResponse);
		when(sequenceGeneratorService.generateSequence(Booking.SEQUENCE_NAME)).thenReturn(1L);
		Booking bookingToAdd = new Booking(1, 123, 240, "standard", 2, 1, 101, newHotel);
	    
	    Booking result = service.addbooking(bookingToAdd);
	    assertEquals(expectedBooking.getPrice(), result.getPrice());
	    assertEquals(hotelResponse.getBody(), result.getHoteldetails());
	    assertEquals(1L, result.getBookingId());
	    
	}

}
