package com.example.hotel.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class HotelAlreadyExist extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String msg;
	
}
