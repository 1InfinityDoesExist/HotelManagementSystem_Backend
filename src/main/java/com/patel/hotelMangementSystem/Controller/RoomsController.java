package com.patel.hotelMangementSystem.Controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patel.hotelMangementSystem.Model.Rooms;
import com.patel.hotelMangementSystem.Repository.RoomsRepository;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/object/rooms")
public class RoomsController {
	@Autowired
	private RoomsRepository roomsRepository;

	@PostMapping(path = "/create")
	public ResponseEntity<?> createRooms(@Valid @RequestBody Rooms rooms, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> error = new LinkedHashMap<String, String>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				error.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(error, HttpStatus.BAD_REQUEST);
		}
		Rooms roomsToDB = roomsRepository.save(rooms);
		return new ResponseEntity<Rooms>(roomsToDB, HttpStatus.CREATED);
	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<?> getRoomsById(@PathVariable(value = "id") Long id) {
		Rooms rooms = roomsRepository.getRoomsById(id);
		if (rooms == null) {
			return new ResponseEntity<String>("Sorry No Data Found For This ID:- " + id, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Rooms>(rooms, HttpStatus.OK);
	}
}
