package com.hotel.rating.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RatingNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String msg;

}
