package com.hotel.rating.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Rating")
public class Rating {
	
	@Transient
    public static final String SEQUENCE_NAME = "rating_sequence";

	@Id
	private long ratingId;
	
	private long userId;
	
	private long hotelId;
	
	
	private int rating;
	
	private String feedback;
	
	
}
