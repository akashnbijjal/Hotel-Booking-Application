package com.example.hotel.controller;

import static org.junit.jupiter.api.Assertions.*;

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

import com.example.hotel.model.Hotel;
import com.example.hotel.service.HotelServiceImpl;

@SpringBootTest
class HotelControllerTest {

	@MockBean
	private HotelServiceImpl service;

	@Autowired
	private HotelController controller;

	@Test
	void test_createhotel() {
		Map<String, Double> roomTypes = new HashMap<String, Double>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);

		Mockito.when(service.create(newHotel)).thenReturn(newHotel);
		ResponseEntity<Hotel> response = controller.createhotel(newHotel);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(newHotel, response.getBody());
	}

	@Test
	void test_getAll() {

		Map<String, Double> roomTypes = new HashMap<String, Double>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);
		Mockito.when(service.getAll()).thenReturn(List.of(newHotel));

		ResponseEntity<List<Hotel>> response = controller.getAll();

		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(List.of(newHotel), response.getBody());
	}

	@Test
	void test_hotelbyid() {
		long hotelId = 1;
		Map<String, Double> roomTypes = new HashMap<String, Double>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);
		Mockito.when(service.getById(hotelId)).thenReturn(newHotel);

		ResponseEntity<Hotel> response = controller.hotelbyid(hotelId);

		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(newHotel, response.getBody());
	}

	@Test
	void test_getbylocation() {
		String location = "Koramangala, Bengaluru";
		Map<String, Double> roomTypes = new HashMap<String, Double>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);

		Mockito.when(service.getByLocation(location)).thenReturn(newHotel);

		ResponseEntity<Hotel> response = controller.getbylocation(location);

		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(newHotel, response.getBody());
	}

	@Test
	void test_updatehotel() {
		long hotelId = 1;
		Map<String, Double> roomTypes = new HashMap<String, Double>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);
		Mockito.when(service.updateHotel(hotelId, newHotel)).thenReturn(newHotel);

		ResponseEntity<Hotel> response = controller.updatehotel(hotelId, newHotel);

		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(newHotel, response.getBody());
	}

}
