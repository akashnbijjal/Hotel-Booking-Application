package com.hotel.rating.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Test exception", response.getBody().getMessage());
		assertEquals("Test request", response.getBody().getDetails());
	}

	@Test
	public void testHandleRatingNotFoundException() throws RatingNotFoundException {
		ExceptionInterceptor interceptor = new ExceptionInterceptor();
		WebRequest request = mock(WebRequest.class);
		when(request.getDescription(false)).thenReturn("Test request");
		RatingNotFoundException exception = new RatingNotFoundException("Test exception");
		ResponseEntity<ErrorDetails> response = interceptor.handleRatingNotFoundException(exception, request);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Test exception", response.getBody().getMessage());
		assertEquals("Test request", response.getBody().getDetails());
	}

}
