package com.patel.hotelMangementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patel.hotelMangementSystem.Model.Hotel;
import com.patel.hotelMangementSystem.Repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	public Hotel createHotel(Hotel hotel) {
		Hotel hotelToDB = hotelRepository.save(hotel);
		return hotelToDB;
	}

}
