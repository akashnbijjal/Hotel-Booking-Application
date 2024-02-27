package com.hotel.booking.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInrecord {

	@Id
	private long checkinId;

	private long userId;
	
	private String name;

	private String email;
	
	private long roomId;

	private LocalDateTime checkInTime;
	
	private LocalDateTime checkOutTime;

	
	
}
