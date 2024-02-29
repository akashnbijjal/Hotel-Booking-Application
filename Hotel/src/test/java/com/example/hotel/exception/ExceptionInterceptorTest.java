package com.example.hotel.exception;

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
		assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
		assertEquals("Test exception", response.getBody().getMessage());
		assertEquals("Test request", response.getBody().getDetails());
	}

    @Test
    public void testHotelAlreadyExistException() {
        HotelAlreadyExist ex = new HotelAlreadyExist("Hotel already exists");
        WebRequest request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("Test request");

        ResponseEntity<ErrorDetails> response = exceptionInterceptor.hotelalreadyexistsexception(ex, request);

        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
        assertEquals("Hotel already exists", response.getBody().getMessage());
        assertEquals("Test request", response.getBody().getDetails());
    }

    @Test
    public void testHotelNotFoundException() {
        HotelNotfound ex = new HotelNotfound("Hotel not found");
        WebRequest request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("Test request");

        ResponseEntity<ErrorDetails> response = exceptionInterceptor.hotelnotfoundexception(ex, request);

        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
        assertEquals("Hotel not found", response.getBody().getMessage());
        assertEquals("Test request", response.getBody().getDetails());
    }

}
