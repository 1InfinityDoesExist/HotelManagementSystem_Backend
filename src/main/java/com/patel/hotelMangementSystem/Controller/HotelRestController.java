package com.patel.hotelMangementSystem.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping(path = "/get/{hotelUniqueId}")
	public ResponseEntity<?> getHotelByUniqueId(@PathVariable(value = "hotelUniqueId") String hotelUniqueId) {
		Hotel hotel = hotelService.getHotelByUniqueId(hotelUniqueId);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);

	}

	@GetMapping(path = "/get")
	public ResponseEntity<?> getAllHotel() {
		List<Hotel> hotelList = hotelService.getAllHotels();
		return new ResponseEntity<List<Hotel>>(hotelList, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{hotelUniqueId}")
	public ResponseEntity<?> deleteHotelByUniqueId(@PathVariable(value = "hotelUniqueId") String hotelUniqueId) {
		String response = hotelService.deleteByUniqueId(hotelUniqueId);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PatchMapping(path = "/update/{hotelUniqueId}")
	public ResponseEntity<?> updateHotelByUniqueId(@Valid @PathVariable(value = "hotelUniqueId") String hotelUniqueId,
			@RequestBody String hotel) {
		Hotel hotelResponse = hotelService.updateHotelByUniqueId(hotelUniqueId, hotel);
		return new ResponseEntity<Hotel>(hotelResponse, HttpStatus.OK);
	}
}
