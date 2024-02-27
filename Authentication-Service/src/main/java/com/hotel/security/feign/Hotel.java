package com.hotel.security.feign;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

	@Transient
    public static final String SEQUENCE_NAME = "hotel_sequence";
	
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
