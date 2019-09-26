package com.patel.hotelMangementSystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailIdAlreadyExistException extends RuntimeException {

	public EmailIdAlreadyExistException(String message) {
		super(message);
	}
}
