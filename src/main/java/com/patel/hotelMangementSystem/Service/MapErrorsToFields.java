package com.patel.hotelMangementSystem.Service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MapErrorsToFields {

	public ResponseEntity<Object> mapHotelError(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> error = new LinkedHashMap<String, String>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				error.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
