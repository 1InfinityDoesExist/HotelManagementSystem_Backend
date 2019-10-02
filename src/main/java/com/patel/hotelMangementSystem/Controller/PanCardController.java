package com.patel.hotelMangementSystem.Controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.patel.hotelMangementSystem.Exception.PanCardNumberAlreadyExistException;
import com.patel.hotelMangementSystem.Model.PanCard;
import com.patel.hotelMangementSystem.Repository.PanCardRepository;
import com.patel.hotelMangementSystem.Service.MapErrorsToFields;
import com.patel.hotelMangementSystem.Utility.ReflectionUtil;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/object/pan")
public class PanCardController {

	@Autowired
	private PanCardRepository panCardRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void setUp() {
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Autowired
	private MapErrorsToFields mapErrorToField;
	ReflectionUtil refUtil = ReflectionUtil.getInstance();

	@PostMapping(path = "/create")
	public ResponseEntity<?> registerPanCard(@Valid @RequestBody PanCard panCard, BindingResult bindingResult) {
		ResponseEntity<?> errorMap = mapErrorToField.mapHotelError(bindingResult);
		if (errorMap != null) {
			return errorMap;
		}
		try {
			PanCard panCardToDB = panCardRepository.save(panCard);
			return new ResponseEntity<PanCard>(panCardToDB, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new PanCardNumberAlreadyExistException("Pan Card Number Already Exist.....!!!!");
		}
	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<?> getPanCardByID(@PathVariable(value = "id") Long id) {
		PanCard panCardFromDB = panCardRepository.getPanCardByID(id);
		if (panCardFromDB == null) {
			return new ResponseEntity<String>("Sorry No Data Found For Id:- " + id, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<PanCard>(panCardFromDB, HttpStatus.OK);
	}

	@GetMapping(path = "/get")
	public ResponseEntity<?> getAllPanCard() {
		List<PanCard> panCardList = panCardRepository.getAllPanCard();
		if (panCardList.size() == 0 || panCardList == null) {
			return new ResponseEntity<String>("Sorry No Data Available", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<PanCard>>(panCardList, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deletePanCardByID(@PathVariable(value = "id") Long id) {
		PanCard panCardFromDB = panCardRepository.getPanCardByID(id);
		if (panCardFromDB == null) {
			return new ResponseEntity<String>("Sorry No Data Found For Id:- " + id, HttpStatus.BAD_REQUEST);
		}
		panCardRepository.delete(panCardFromDB);
		return new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
	}

	@PatchMapping(path = "/update/{id}")
	public ResponseEntity<?> updatePanCardByID(@Valid @RequestBody String panCard, @PathVariable(value = "id") Long id)
			throws java.text.ParseException, JsonParseException, JsonMappingException, IOException {
		PanCard panCardFromDB = panCardRepository.getPanCardByID(id);
		if (panCardFromDB == null) {
			return new ResponseEntity<String>("Sorry No Data Found For Id:- " + id, HttpStatus.BAD_REQUEST);
		}

		PanCard panCardFromPayload = objectMapper.readValue(panCard, PanCard.class);
		Date date = panCardFromPayload.getCreationDate();
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(panCard);
			for (Iterator iterator = ((Map<String, String>) obj).keySet().iterator(); iterator.hasNext();) {
				String props = (String) iterator.next();
				if (props.equals("creationDate")) {
					panCardFromDB.setCreationDate(date);

				} else {
					refUtil.getSetterMethod("PanCard", props).invoke(panCardFromDB, obj.get(props));
				}
			}
		} catch (ParseException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panCardRepository.save(panCardFromDB);
		return new ResponseEntity<PanCard>(panCardFromDB, HttpStatus.OK);
	}

}
