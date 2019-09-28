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

import com.patel.hotelMangementSystem.Model.House;
import com.patel.hotelMangementSystem.Repository.HouseRepository;

@RestController
@CrossOrigin
@RequestMapping(path = "api/object/house")
public class HouseController {
	@Autowired
	private HouseRepository houseRepository;

	@PostMapping(path = "/create")
	public ResponseEntity<?> createHouse(@Valid @RequestBody House house, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> error = new LinkedHashMap<String, String>();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				error.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(error, HttpStatus.BAD_REQUEST);
		}

		House houseToDB = houseRepository.save(house);
		return new ResponseEntity<House>(houseToDB, HttpStatus.OK);
	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<?> getHouseById(@PathVariable(value = "id") Long id) {
		House house = houseRepository.getHouseByID(id);
		if (house == null) {
			return new ResponseEntity<String>("Sorry No Value Found For This id:- " + id, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<House>(house, HttpStatus.OK);
	}
}
