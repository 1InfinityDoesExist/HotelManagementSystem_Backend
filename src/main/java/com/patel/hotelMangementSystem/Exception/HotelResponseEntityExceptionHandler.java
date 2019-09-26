package com.patel.hotelMangementSystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class HotelResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<?> hotelResponseEntityExceptionHandler(HotelUniqueIdException ex, WebRequest webRequest) {
		HotelUniqueIdExceptionResponse response = new HotelUniqueIdExceptionResponse(ex.getMessage());
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<?> hotelResponseEntityExponseHandler(RoomUniqueIdException ex, WebRequest webRequest) {
		RoomUniqueIdExceptionResponse response = new RoomUniqueIdExceptionResponse(ex.getMessage());
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<?> aadharCardAlreadyExistHandler(AadharCardNumberAlreadyExistException ex,
			WebRequest webRequest) {
		AadharCardNumberAlreadyExistResponse response = new AadharCardNumberAlreadyExistResponse(ex.getMessage());
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<?> panCardNumberAlreadyExistHandler(PanCardNumberAlreadyExistException ex,
			WebRequest webRequest) {
		PanCardNumberAlreadyExistResponse response = new PanCardNumberAlreadyExistResponse(ex.getMessage());
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<?> emailIdAlreadyExistException(EmailIdAlreadyExistException ex, WebRequest webRequest) {
		EmailIdAlreadyExistResponse response = new EmailIdAlreadyExistResponse(ex.getMessage());
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<?> phoneNumberAlreadyExistHandler(PhoneNumberAlreadyExist ex, WebRequest webRequest) {
		PhoneNumberAlreadyExistResponse response = new PhoneNumberAlreadyExistResponse(ex.getMessage());
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

}
