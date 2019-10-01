package com.patel.hotelMangementSystem.Controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

import com.patel.hotelMangementSystem.Exception.AadharCardNumberAlreadyExistException;
import com.patel.hotelMangementSystem.Model.AadharCard;
import com.patel.hotelMangementSystem.Model.Address;
import com.patel.hotelMangementSystem.Repository.AadharCardRepository;
import com.patel.hotelMangementSystem.Service.MapErrorsToFields;
import com.patel.hotelMangementSystem.Utility.ReflectionUtil;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/object/aadhar")
public class AadharCardRController {

	@Autowired
	private AadharCardRepository aadharCardRepository;

	@Autowired
	private MapErrorsToFields mapErrorToFields;

	@Autowired
	private AadharCardRController aadharCardController;

	ReflectionUtil refUtil = ReflectionUtil.getInstance();

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

	@GetMapping(path = "/get")
	public ResponseEntity<?> getAllAadharCard() {
		List<AadharCard> aadharList = aadharCardRepository.getAllAadhar();
		if (aadharList.size() == 0 || aadharList == null) {
			return new ResponseEntity<String>("Sorry No Data Found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<AadharCard>>(aadharList, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteAadharCard(@PathVariable(value = "id") Long id) {
		AadharCard aadharCardFromDB = aadharCardRepository.getAadharCardByID(id);
		if (aadharCardFromDB == null) {
			return new ResponseEntity<String>("Sorry No Data Found for This ID:-" + id, HttpStatus.BAD_REQUEST);
		}
		aadharCardRepository.delete(aadharCardFromDB);
		return new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
	}

	@PatchMapping(path = "/update/{id}")
	public ResponseEntity<?> updateAadharCard(@PathVariable(value = "id") Long id, @RequestBody String aadharCard, BindingResult bindingResult) {
		AadharCard aadharCardFromDB = aadharCardRepository.getAadharCardByID(id);
		if (aadharCardFromDB == null) {
			return new ResponseEntity<String>("Sorry No Data Found for This ID:-" + id, HttpStatus.BAD_REQUEST);
		}
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(aadharCard);
			for (Iterator iterator = ((Map<String, String>) obj).keySet().iterator(); iterator.hasNext();) {

				String props = (String) iterator.next();
				if (props.equals("address")) {
					if (obj.get("address") != null) {
						JSONObject addObject = (JSONObject) obj.get("address");
						System.out.println(addObject);
						if (aadharCardFromDB.getAddress() == null) {
							aadharCardFromDB.setAddress(new Address());
						}
						for (Object src : addObject.keySet()) {
							String prop = (String) src;
							refUtil.getSetterMethod("Address", prop).invoke(aadharCardFromDB.getAddress(),
									addObject.get(prop));
						}
					} else {
						aadharCardFromDB.setAddress(null);
					}
				} else {
					refUtil.getSetterMethod("AadharCard", props).invoke(aadharCardFromDB, obj.get(props));
				}
			}
		} catch (ParseException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aadharCardController.registerAadharCard(aadharCardFromDB, bindingResult);
		//aadharCardRepository.save(aadharCardFromDB);
		return new ResponseEntity<AadharCard>(aadharCardFromDB, HttpStatus.OK);
	}
}
