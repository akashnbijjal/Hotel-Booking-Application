package com.example.hotel.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotel.exception.HotelAlreadyExist;
import com.example.hotel.exception.HotelNotfound;
import com.example.hotel.model.Hotel;
import com.example.hotel.repository.hotelrepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private hotelrepository repo;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

	private static final int DEFAULT_RATING = 3;

	@Override
	public Hotel create(Hotel hotel) {
		if (repo.existsByName(hotel.getName())) {
			logger.warn("Hotel already exists with name {}");
			throw new HotelAlreadyExist("Hotel already exist with name : " + hotel.getName());
		} else {
			hotel.setRating(DEFAULT_RATING);
			logger.info("Hotel details added successfully");
			hotel.setHotelId(sequenceGeneratorService.generateSequence(Hotel.SEQUENCE_NAME));
			return repo.save(hotel);

		}
	}

	@Override
	public List<Hotel> getAll() {
		logger.info("Fetching all hotels...");
		List<Hotel> list = repo.findAll();
		logger.info("Found {} hotels", list.size());
		return list;
	}

	@Override
	public Hotel getById(long hotelId) {
		logger.info("Fetching hotel by ID: {}", hotelId);
		Hotel hotel = repo.findById(hotelId).orElseThrow(() -> {
			logger.error("Hotel not found with ID: {}", hotelId);
			return new HotelNotfound("Hotel Not Found with ID: " + hotelId);
		});
		logger.info("Found hotel: {}", hotel);
		return hotel;
	}

	@Override
	public Hotel getByLocation(String location) {
		logger.info("Fetching hotel by location: {}", location);
		Hotel hotel = repo.findByLocationIgnoreCaseContaining(location);
		if (hotel == null) {
			logger.warn("No hotel found for location: {}", location);
			throw new HotelNotfound("No hotel found for location: " + location);
		}
		logger.info("Found hotel: {}", hotel);
		return hotel;
	}

	@Override
	public Hotel updateHotel(long hotelId, Hotel updatedHotel) {
		logger.info("Updating hotel with ID: {}", hotelId);
		Optional<Hotel> optionalExistingHotel = repo.findById(hotelId);

		if (optionalExistingHotel.isPresent()) {
			Hotel existingHotel = optionalExistingHotel.get();

			// Update hotel fields if provided in updatedHotel
			if (updatedHotel.getName() != null) {
				existingHotel.setName(updatedHotel.getName());
			}
			if (updatedHotel.getLocation() != null) {
				existingHotel.setLocation(updatedHotel.getLocation());
			}
			if (updatedHotel.getRating() != 0) {
				existingHotel.setRating(updatedHotel.getRating());
			}
			if (updatedHotel.getAbout() != null) {
				existingHotel.setAbout(updatedHotel.getAbout());
			}

			logger.info("Hotel updated successfully: {}", existingHotel);
			return repo.save(existingHotel);
		} else {
			logger.error("Hotel not found with ID: {}", hotelId);
			throw new HotelNotfound("Hotel Not Found with ID: " + hotelId);
		}
	}

}
