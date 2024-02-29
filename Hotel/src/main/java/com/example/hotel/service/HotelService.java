package com.example.hotel.service;

import java.util.List;

import com.example.hotel.model.Hotel;

public interface HotelService {

	Hotel create(Hotel hotel);

	List<Hotel> getAll();

	public Hotel getById(long hotelId);

	 public Hotel getByLocation(String location);

	Hotel updateHotel(long hotelid, Hotel hotel);
}
