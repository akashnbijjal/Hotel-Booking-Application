package com.hotel.booking.model;

import java.util.Map;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
	@Id
	private long hotelId;

	private String name;

	private String location;

	private String about;

	private int rating;

	private String email;
	
	private String contact;
	
	private Map<String, Double> roomtypes;
	
	

}
