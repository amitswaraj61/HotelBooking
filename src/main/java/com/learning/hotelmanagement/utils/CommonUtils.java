package com.learning.hotelmanagement.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.learning.hotelmanagement.handler.GenericResponseHandler;

public class CommonUtils {

	public static ResponseEntity<?> createBuildResponse(Object object, String message, HttpStatusCode status) {
		GenericResponseHandler handler = new GenericResponseHandler();
		handler.setStatus(status.value());
		handler.setMessage(message);
		handler.setData(object);
		return handler.create();
	}
}
