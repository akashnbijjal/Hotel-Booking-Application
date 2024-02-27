package com.hotel.booking.exception;

import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(BookingNotFound.class)
	public final ResponseEntity<ErrorDetails> handleBookingException(BookingNotFound ex, WebRequest request)
			throws BookingNotFound {
		ErrorDetails errordetails = new ErrorDetails(LocalDate.now(), ex.getMsg(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.NOT_FOUND);
	}
	
	

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserException(UserNotFoundException ex, WebRequest request)
			throws UserNotFoundException {
		ErrorDetails errordetails = new ErrorDetails(LocalDate.now(), ex.getMsg(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errordetails, HttpStatus.NOT_FOUND);
	}
	
}
