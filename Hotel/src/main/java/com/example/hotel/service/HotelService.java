package com.example.hotel.service;

import java.util.List;

import com.example.hotel.model.Hotel;

public interface HotelService {

	Hotel create(Hotel hotel);

	List<Hotel> getAll();

	Hotel getbyid(long hotelid);

	Hotel getbylocation(String location);

	Hotel updateHotel(long hotelid, Hotel hotel);
}
