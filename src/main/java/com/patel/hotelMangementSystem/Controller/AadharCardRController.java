package com.patel.hotelMangementSystem.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patel.hotelMangementSystem.Exception.AadharCardNumberAlreadyExistException;
import com.patel.hotelMangementSystem.Model.AadharCard;
import com.patel.hotelMangementSystem.Repository.AadharCardRepository;
import com.patel.hotelMangementSystem.Service.MapErrorsToFields;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/object/aadhar")
public class AadharCardRController {

	@Autowired
	private AadharCardRepository aadharCardRepository;

	@Autowired
	private MapErrorsToFields mapErrorToFields;

	@PostMapping(path = "/create")
	public ResponseEntity<?> registerAadharCard(@Valid @RequestBody AadharCard aadharCard,
			BindingResult bindingResult) {
		ResponseEntity<?> errorMap = mapErrorToFields.mapHotelError(bindingResult);
		if (errorMap != null) {
			return errorMap;
		}
		try {
			AadharCard aadharCardToDB = aadharCardRepository.save(aadharCard);
			return new ResponseEntity<AadharCard>(aadharCardToDB, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new AadharCardNumberAlreadyExistException("Aadhar Card Number Already Exist");
		}
	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<?> getAadharCardById(@PathVariable(value = "id") Long id) {
		AadharCard aadharCardFromDB = aadharCardRepository.getAadharCardByID(id);
		if (aadharCardFromDB == null) {
			return new ResponseEntity<String>("Sorry No Data Found for This ID:-" + id, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<AadharCard>(aadharCardFromDB, HttpStatus.OK);
	}
}
