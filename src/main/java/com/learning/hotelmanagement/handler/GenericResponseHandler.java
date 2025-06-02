package com.learning.hotelmanagement.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
public class GenericResponseHandler {

	private int status;
	private String message;
	private Object data;

	public ResponseEntity<?> create() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("status", status);
		map.put("message", message);
		map.put("data", data);
		return new ResponseEntity<>(map, HttpStatus.valueOf(status));
	}
}
