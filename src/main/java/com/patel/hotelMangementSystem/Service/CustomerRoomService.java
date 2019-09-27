package com.patel.hotelMangementSystem.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patel.hotelMangementSystem.Exception.AadharCardNumberAlreadyExistException;
import com.patel.hotelMangementSystem.Model.Customer;
import com.patel.hotelMangementSystem.Model.CustomerRoom;
import com.patel.hotelMangementSystem.Model.Room;
import com.patel.hotelMangementSystem.Repository.CustomerRepository;
import com.patel.hotelMangementSystem.Repository.CustomerRoomRepository;
import com.patel.hotelMangementSystem.Repository.RoomRepository;

@Service
public class CustomerRoomService {

	@Autowired
	private CustomerRoomRepository customerRoomRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public CustomerRoom persisitCustomerRoomInstance(CustomerRoom customerRoom) {
		try {

			Room room = roomRepository.getRoomByUniqueId(customerRoom.getRoomUniqueId());
			Customer customer = customerRepository.getCustomerByAadharNumber(customerRoom.getAadharCardNumber());

			// This part is for customer Details
			customerRoom.setPanCardNumber(customer.getPanCardNumber());
			customerRoom.setPhoneNumber(customer.getPhoneNumber());
			customerRoom.setEmail(customer.getEmail());
			customerRoom.setAddress(customer.getAddress());

			// This Part is For HotelRoomDetails
			
			customerRoom.setAcNonAcType(room.getRoomColdness());
			customerRoom.setNoOfBeds(room.getNoOfBeds());
			customerRoom.setTotalCost(room.getTotalCost());
			customerRoom.setDeleteFlag(false);
			
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

	public List<CustomerRoom> getAllCustomerAvailable(String status) {
		List<CustomerRoom> customerRoomList = customerRoomRepository.getAllCustomerDetailsByStatus(status);
		if (customerRoomList.size() == 0 || customerRoomList == null) {
			throw new AadharCardNumberAlreadyExistException("Sorry No Details Details Found For This Id");
		}
		return customerRoomList;
	}

}
