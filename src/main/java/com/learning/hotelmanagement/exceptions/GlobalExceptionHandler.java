package com.learning.hotelmanagement.exceptions;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learning.hotelmanagement.utils.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<String> al = new LinkedList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String defaultMessage = error.getDefaultMessage();
			System.out.println(defaultMessage);
			al.add(defaultMessage);
		});
		ErrorResponse api = new ErrorResponse(al.get(0), false, HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(api, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String, String>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		Map<String, String> response = new LinkedHashMap<>();
		response.put("Message", ex.getMessage());
		response.put("Error", "HTTP method not supported");
		return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> resourceNotFoundException(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ErrorResponse api = new ErrorResponse(message, false, HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(api, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
		String message = "Please Add Proper Request";
		ErrorResponse api = new ErrorResponse(message, false, HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(api, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> dataIntegrityViolationException(DataIntegrityViolationException ex) {
		String message = "Please insert Unique Data";
		ErrorResponse api = new ErrorResponse(message, false, HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(api, HttpStatus.BAD_REQUEST);
	}
}