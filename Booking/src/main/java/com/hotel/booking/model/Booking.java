package com.hotel.booking.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Booking")
public class Booking {
	
	@Transient
    public static final String SEQUENCE_NAME = "bookings_sequence";

	@Id
	private long bookingId;
	
	private long userId;
	
	private double price;
	
	
	private String roomtype;
	
	private int quantity;
	
	private long hotelId;
	
	private long roomId;
	
	
	@Transient
	private Hotel hoteldetails;
	
	
}
