package com.hotel.booking.exception;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

@SpringBootTest
class ExceptionInterceptorTest {

	@MockBean
	private ExceptionInterceptor exceptionInterceptor;

	@Test
	public void testHandleAllException() throws Exception {

		ExceptionInterceptor interceptor = new ExceptionInterceptor();
		WebRequest request = mock(WebRequest.class);
		when(request.getDescription(false)).thenReturn("Test request");
		Exception exception = new Exception("Test exception");

		ResponseEntity<ErrorDetails> response = interceptor.handleAllException(exception, request);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Test exception", response.getBody().getMessage());
		assertEquals("Test request", response.getBody().getDescription());
	}

	@Test
	public void testHandlehandleBookingException() throws BookingNotFound {

		ExceptionInterceptor interceptor = new ExceptionInterceptor();
		WebRequest request = mock(WebRequest.class);
		when(request.getDescription(false)).thenReturn("Test request");
		BookingNotFound exception = new BookingNotFound("Test exception");

		ResponseEntity<ErrorDetails> response = interceptor.handleBookingException(exception, request);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Test exception", response.getBody().getMessage());
		assertEquals("Test request", response.getBody().getDescription());
	}

	@Test
	public void testHandlehandlehandleUserException() throws UserNotFoundException {

		ExceptionInterceptor interceptor = new ExceptionInterceptor();
		WebRequest request = mock(WebRequest.class);
		when(request.getDescription(false)).thenReturn("Test request");
		UserNotFoundException exception = new UserNotFoundException("Test exception");

		ResponseEntity<ErrorDetails> response = interceptor.handleUserException(exception, request);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Test exception", response.getBody().getMessage());
		assertEquals("Test request", response.getBody().getDescription());
	}

}
