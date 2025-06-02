package com.learning.hotelmanagement.utils;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {

	private String message;
	private int status;
	private boolean success;

	public ErrorResponse(String message, boolean success, int status) {
		this.message = message;
		this.success = success;
		this.status = status;
	}
}