package com.patel.hotelMangementSystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoomUniqueIdException extends RuntimeException {
	public RoomUniqueIdException(String message) {
		super(message);
	}
}
