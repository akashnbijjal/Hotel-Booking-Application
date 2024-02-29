package com.example.hotel.exception;

import java.time.LocalDateTime;

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
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(HotelAlreadyExist.class)
	public final ResponseEntity<ErrorDetails> hotelalreadyexistsexception(HotelAlreadyExist ex, WebRequest request)throws HotelAlreadyExist{
		ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),ex.getMsg(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_GATEWAY);
	}
	@ExceptionHandler(HotelNotfound.class)
	public final ResponseEntity<ErrorDetails> hotelnotfoundexception(HotelNotfound ex, WebRequest request)throws HotelNotfound{
		ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),ex.getMsg(),request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_GATEWAY);
	}
	
	
}
