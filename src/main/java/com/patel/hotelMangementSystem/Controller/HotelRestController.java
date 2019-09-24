package com.patel.hotelMangementSystem.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patel.hotelMangementSystem.Model.Hotel;
import com.patel.hotelMangementSystem.Service.HotelService;
import com.patel.hotelMangementSystem.Service.MapErrorsToFields;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/object/hotel")
public class HotelRestController {
	@Autowired
	private HotelService hotelService;

	@Autowired
	private MapErrorsToFields mapErrorsToFields;

	@PostMapping(path = "/create")
	public ResponseEntity<?> saveHotel(@Valid @RequestBody Hotel hotel, BindingResult bindingResult) {

		ResponseEntity<?> response = mapErrorsToFields.mapHotelError(bindingResult);
		if (response != null) {
			return response;
		}
		Hotel hotelToDB = hotelService.createHotel(hotel);
		return new ResponseEntity<Hotel>(hotelToDB, HttpStatus.CREATED);
	}
	
	
}
