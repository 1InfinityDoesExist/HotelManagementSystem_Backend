package com.patel.hotelMangementSystem.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patel.hotelMangementSystem.Exception.HotelUniqueIdException;
import com.patel.hotelMangementSystem.Model.Address;
import com.patel.hotelMangementSystem.Model.Hotel;
import com.patel.hotelMangementSystem.Repository.HotelRepository;
import com.patel.hotelMangementSystem.Utility.ReflectionUtil;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	ReflectionUtil refUtil = ReflectionUtil.getInstance();

	public Hotel createHotel(Hotel hotel) {
		try {
			Hotel hotelToDB = hotelRepository.save(hotel);
			return hotelToDB;
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
		if (allHotels.size() == 0 || allHotels != null) {
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

	public Hotel updateHotelByUniqueId(String hotelUniqueId, String hotel) {
		Hotel hotelFromDB = hotelRepository.getHotelByUniqueId(hotelUniqueId);
		if (hotelFromDB == null) {
			throw new HotelUniqueIdException("Sorry No Hotel Found With UniqueId:- " + hotelUniqueId);
		}
		JSONParser parser = new JSONParser();
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
				}
				refUtil.getSetterMethod("Hotel", propName).invoke(hotelFromDB, obj.get(propName));
			}
		} catch (ParseException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Hotel hotelResponse = hotelRepository.save(hotelFromDB);
		return hotelResponse;
	}

}
