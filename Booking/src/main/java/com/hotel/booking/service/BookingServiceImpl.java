package com.hotel.booking.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.hotel.booking.exception.BookingNotFound;
import com.hotel.booking.exception.UserNotFoundException;
import com.hotel.booking.feign.HotelService;
import com.hotel.booking.model.Booking;
import com.hotel.booking.model.Hotel;
import com.hotel.booking.repository.bookingrepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private bookingrepository repo;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService feignclient;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Override
	public Booking addbooking(Booking booking) {
		ResponseEntity<Hotel> hotel = feignclient.hotelbyid(booking.getHotelId());
		if (booking.getHotelId() == hotel.getBody().getHotelId()) {
			String room = booking.getRoomtype();
			int quantity = booking.getQuantity();
			Map<String, Double> roomtypes = hotel.getBody().getRoomtypes();
			if (roomtypes.containsKey(room)) {
				Double price = roomtypes.get(room) * quantity;
				booking.setPrice(price);
			}
		}
		;

		booking.setHoteldetails(hotel.getBody());
		booking.setBookingId(sequenceGeneratorService.generateSequence(Booking.SEQUENCE_NAME));
		repo.save(booking);

		return repo.save(booking);
	}

	@Override
	public List<Booking> allbookings() {
		List<Booking> list = repo.findAll();
		for (Booking booking : list) {
			Hotel hotel = restTemplate.getForObject("http://localhost:8081/hotel/id/" + booking.getHotelId(),
					Hotel.class);

			booking.setHoteldetails(hotel);
		}
		return list;
	}

	@Override
	public Booking getbookingbyid(long bookingId) throws BookingNotFound {
		if (repo.existsById(bookingId)) {
			Booking booking = repo.findById(bookingId).get();
//			Hotel hotel = restTemplate.getForObject("http://localhost:8081/hotel/id/" + booking.getHotelId(),
//					Hotel.class);
			ResponseEntity<Hotel> hotel = feignclient.hotelbyid(booking.getBookingId());

			booking.setHoteldetails(hotel.getBody());
			return booking;
		} else {
			throw new BookingNotFound("Booking not found with ID: " + bookingId);
		}

	}

	@Override
	public Booking getbookingbyuserid(long userId) {
		if (repo.existsByUserId(userId)) {
			Booking booking = repo.findByUserId(userId);
			return booking;
		} else {
			throw new UserNotFoundException("User not found with ID: " + userId);
		}

	}

	@Override
	public String deletebyid(long bookingId) {
		if (repo.existsById(bookingId)) {
			repo.deleteById(bookingId);
			return "Deleted successfully with ID :" + bookingId;
		}
		return "BookingId not found";
	}

}
