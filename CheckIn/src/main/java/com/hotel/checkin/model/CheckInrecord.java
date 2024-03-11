package com.hotel.checkin.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("checkin")
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
