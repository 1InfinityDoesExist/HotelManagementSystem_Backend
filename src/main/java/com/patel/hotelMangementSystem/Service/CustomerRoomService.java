package com.patel.hotelMangementSystem.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patel.hotelMangementSystem.Exception.AadharCardNumberAlreadyExistException;
import com.patel.hotelMangementSystem.Model.CustomerRoom;
import com.patel.hotelMangementSystem.Repository.CustomerRoomRepository;

@Service
public class CustomerRoomService {

	@Autowired
	private CustomerRoomRepository customerRoomRepository;

	public CustomerRoom persisitCustomerRoomInstance(CustomerRoom customerRoom) {
		try {
			
			CustomerRoom customerRoomToDB = customerRoomRepository.save(customerRoom);
			return customerRoomToDB;
		} catch (Exception e) {
			throw new AadharCardNumberAlreadyExistException(
					"Soory Details Of CustomerRoom Cannot Be Persisted In Database");
		}
	}

	public CustomerRoom getCustomerRoomByCheckOut(String status, String aadharCardNumber, Date chekOutDate) {

		CustomerRoom customerRoom = customerRoomRepository.getCustomerByTodayDate(status, aadharCardNumber, new Date());
		if (customerRoom == null) {
			throw new AadharCardNumberAlreadyExistException("Sorry No Such Data Found For this Aadhar Card Number");
		}
		return customerRoom;
	}
	
	public List<CustomerRoom> getAllCustomerAvailable(String status){
		List<CustomerRoom> customerRoomList = customerRoomRepository.getAllCustomerDetailsByStatus(status );
		if(customerRoomList.size() == 0 || customerRoomList == null) {
			throw new AadharCardNumberAlreadyExistException("Sorry No Details Details Found For This Id");
		}
		return customerRoomList;
	}
	
	
}
