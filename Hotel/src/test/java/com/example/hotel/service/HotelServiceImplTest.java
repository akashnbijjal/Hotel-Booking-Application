package com.example.hotel.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.hotel.exception.HotelNotfound;
import com.example.hotel.model.Hotel;
import com.example.hotel.repository.hotelrepository;

@SpringBootTest
class HotelServiceImplTest {

	@Autowired
	private HotelServiceImpl service;

	@MockBean
	private hotelrepository HotelRepository;

	@MockBean
	private SequenceGeneratorService sequenceGeneratorService;

	@Test
	void test_createHotel_success() {
		Map<String, Double> roomTypes = new HashMap<String, Double>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);
		Mockito.when(HotelRepository.existsByName(newHotel.getName())).thenReturn(false);
		Mockito.when(HotelRepository.save(newHotel)).thenReturn(newHotel);
		Mockito.when(sequenceGeneratorService.generateSequence(Hotel.SEQUENCE_NAME)).thenReturn(1L);
		Hotel savedHotel = service.create(newHotel);
		assertEquals(newHotel, savedHotel);
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
		Mockito.when(HotelRepository.findAll()).thenReturn(List.of(newHotel));
		List<Hotel> allHotels = service.getAll();
		assertEquals(List.of(newHotel), allHotels);
	}

	@Test
	void test_getById_notFound() {
		long hotelId = 1;
		Mockito.when(HotelRepository.findById(hotelId)).thenReturn(Optional.empty());
		HotelNotfound exception = assertThrows(HotelNotfound.class, () -> {
			service.getById(hotelId);
		});
		assertEquals("Hotel Not Found with ID: " + hotelId, exception.getMsg());
	}

	@Test
	void test_getById_success() {
		long hotelId = 1;
		Map<String, Double> roomTypes = new HashMap<String, Double>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);
		Mockito.when(HotelRepository.findById(hotelId)).thenReturn(Optional.of(newHotel));
		Hotel hotel = service.getById(hotelId);
		assertEquals(newHotel, hotel);
	}

	@Test
	void test_getByLocation_success() {
		String location = "Koramangala, Bengaluru";
		Map<String, Double> roomTypes = new HashMap<String, Double>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);
		Mockito.when(HotelRepository.findByLocationIgnoreCaseContaining(location)).thenReturn(newHotel);
		Hotel hotel = service.getByLocation(location);
		assertEquals(newHotel, hotel);
	}

	@Test
	void test_getByLocation_notFound() {
		String location = "xxxxx";
		Mockito.when(HotelRepository.findByLocationIgnoreCaseContaining(location)).thenReturn(null);

		HotelNotfound exception = assertThrows(HotelNotfound.class, () -> {
			service.getByLocation(location);
		});
		assertEquals("No hotel found for location: " + location, exception.getMsg());

	}

	@Test
	void test_updateHotel_success() {
		long hotelId = 1;
		Map<String, Double> roomTypes = new HashMap<String, Double>();
		roomTypes.put("standard", 120.0);
		roomTypes.put("deluxe", 180.0);
		roomTypes.put("suite", 300.0);
		Hotel newHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);
		Hotel updatedHotel = new Hotel(1, "Bengaluru Grand", "Koramangala, Bengaluru",
				"Experience luxury in the heart of Bengaluru.", 3.6666666666666665, "info@bengalurugrand.com",
				"+91 9876543210", roomTypes);
		Mockito.when(HotelRepository.findById(hotelId)).thenReturn(Optional.of(updatedHotel));
		Mockito.when(HotelRepository.save(newHotel)).thenReturn(updatedHotel);
		Hotel hotel = service.updateHotel(hotelId, updatedHotel);
		assertEquals(updatedHotel, hotel);
	}

	@Test
	void test_updateHotel_notFound() {
		long hotelId = 1;
		Hotel updatedHotel = new Hotel();
		Mockito.when(HotelRepository.findById(hotelId)).thenReturn(Optional.empty());

		HotelNotfound exception = assertThrows(HotelNotfound.class, () -> {
			service.updateHotel(hotelId, updatedHotel);
		});
		assertEquals("Hotel Not Found with ID: " + hotelId, exception.getMsg());

	}

}
