package com.example.hotel.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotel.exception.HotelAlreadyExist;
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
		List<Hotel> list = repo.findAll();
		return list;
	}

	@Override
	public Hotel getbyid(long hotelid) {
		Hotel hotel = repo.findById(hotelid).get();
		return hotel;

	}

	@Override
	public Hotel getbylocation(String location) {
		Hotel hotel = repo.findByLocationIgnoreCaseContaining(location);
		return hotel;
	}

	@Override
	public Hotel updateHotel(long hotelid, Hotel updatedHotel) {
		Optional<Hotel> optionalExistingHotel = repo.findById(hotelid);

		if (optionalExistingHotel.isPresent()) {
			Hotel existingHotel = optionalExistingHotel.get();

			// Check each field of the updatedHotel and update only if the field is not null
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

			return repo.save(existingHotel);
		}
		return null;

	}

}
