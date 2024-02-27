package com.example.hotel.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

	@Id
	private long ratingId;
	
	private long userId;
	
	private long hotelId;
	
	
	private int rating;
	
	private String feedback;
	
}
