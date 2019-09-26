package com.patel.hotelMangementSystem.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.patel.hotelMangementSystem.Exception.HotelUniqueIdException;
import com.patel.hotelMangementSystem.Model.Address;
import com.patel.hotelMangementSystem.Model.Hotel;
import com.patel.hotelMangementSystem.Repository.HotelRepository;
import com.patel.hotelMangementSystem.Utility.ReflectionUtil;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void setUp() {
		objectMapper.registerModule(new JavaTimeModule());
	}

	ReflectionUtil refUtil = ReflectionUtil.getInstance();

	public Hotel createHotel(Hotel hotel) {
		try {
			hotel.setDeleteFlag(false);
			hotel.setNotes("Thank You For Visiting " + hotel.getName());
			Hotel hotelToDB = hotelRepository.save(hotel);

			if (hotelToDB != null) {
				String uniqueIdForHotel = hotelToDB.getName() + "_" + hotelToDB.getId();
				hotelRepository.updateHotelUniqueId(uniqueIdForHotel, hotelToDB.getId());
				return hotelRepository.getHotelByUniqueId(uniqueIdForHotel);
			}
			return null;

		} catch (Exception e) {
			throw new HotelUniqueIdException(
					"Sorry Could Not Persist Hotel Instance In Database...Please Check The Mandatory Fields");
		}
	}

	public Hotel getHotelByUniqueId(String uniqueId) {
		Hotel hotel = hotelRepository.getHotelByUniqueId(uniqueId);
		if (hotel == null) {
			throw new HotelUniqueIdException("Sorry No Hotel Found With UniqueID:- " + uniqueId);
		}
		return hotel;
	}

	public List<Hotel> getAllHotels() {
		List<Hotel> allHotels = hotelRepository.getAllHotels();
		if (allHotels.size() == 0 || allHotels == null) {
			throw new HotelUniqueIdException("Sorry Not Data Found In The Hotel Table");
		}
		return allHotels;
	}

	public String deleteByUniqueId(String hotelUniqueId) {
		Hotel hotel = hotelRepository.getHotelByUniqueId(hotelUniqueId);
		if (hotel == null) {
			throw new HotelUniqueIdException("Sorry No Hotel Found With UniqueID:- " + hotelUniqueId);
		}
		hotelRepository.deleteHotel(hotelUniqueId);
		return "SuccessFully Deleted";
	}

	public Hotel updateHotelByUniqueId(String hotelUniqueId, String hotel)
			throws JsonParseException, JsonMappingException, IOException {
		Hotel hotelFromDB = hotelRepository.getHotelByUniqueId(hotelUniqueId);
		if (hotelFromDB == null) {
			throw new HotelUniqueIdException("Sorry No Hotel Found With UniqueId:- " + hotelUniqueId);
		}

		Address address = null;
		Hotel hotel1 = null;
		JSONParser parser = new JSONParser();
		hotel1 = objectMapper.readValue(hotel, Hotel.class);
		address = hotel1.getAddress();
		try {
			JSONObject obj = (JSONObject) parser.parse(hotel);
			for (Iterator iterator = ((Map<String, String>) obj).keySet().iterator(); iterator.hasNext();) {
				String propName = (String) iterator.next();
				if (propName.equals("address")) {
					if (obj.get("address") != null) {
						JSONObject addressObj = (JSONObject) obj.get("address");
						if (hotelFromDB.getAddress() == null) {
							hotelFromDB.setAddress(new Address());
						}
						for (Object str : addressObj.keySet()) {
							String addressProps = (String) str;
							refUtil.getSetterMethod("Address", addressProps).invoke(hotelFromDB.getAddress(),
									addressObj.get(addressProps));
						}
					} else {
						hotelFromDB.setAddress(null);
					}
				} else {
					refUtil.getSetterMethod("Hotel", propName).invoke(hotelFromDB, obj.get(propName));
				}

			}
		} catch (ParseException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new HotelUniqueIdException("Sorry unable to update");
		}
		Hotel hotelResponse = hotelRepository.save(hotelFromDB);
		return hotelResponse;
	}

}
