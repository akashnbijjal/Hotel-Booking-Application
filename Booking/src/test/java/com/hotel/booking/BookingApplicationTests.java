package com.hotel.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hotel.booking.model.Booking;

import com.hotel.booking.repository.bookingrepository;
import com.hotel.booking.service.BookingServiceImpl;

@SpringBootTest
class BookingApplicationTests {

	@Autowired
	private BookingServiceImpl service;

	@MockBean
	private bookingrepository repository;

//	@Test
//	public void TestAllBookings() {
//		when(repository.findAll())
//				.thenReturn(Stream.of(new Booking(1, 1, "1200", 123, new ArrayList<>())).collect(Collectors.toList()));
//		assertEquals(1, service.allbookings().size());
//	}
//
//	@Test
//	public void testGetBookingById() {
//
//		long bookingId = 1;
//		long userId = 1;
//		String price = "1200";
//		long hotelId = 123;
//
//		Booking booking = new Booking(bookingId, userId, price, hotelId, new ArrayList<>());
//		when(repository.findById(bookingId)).thenReturn(Optional.of(booking));
//
//		Booking result = service.getbookingbyid(bookingId);
//
//		assertEquals(booking, result);
//	}

}
