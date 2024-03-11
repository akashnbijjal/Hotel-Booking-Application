package com.hotel.booking.model;

import java.util.Map;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@AllArgsConstructor
public class Hotel {
	@Id
	private long hotelId;

	private String name;

	private String location;

	private String about;

	private Double rating;

	private String email;

	private String contact;

	private Map<String, Double> roomtypes;

	public long getHotelId() {
		return hotelId;
	}

	public Map<String, Double> getRoomtypes() {
		return roomtypes;
	}

}
