package com.patel.hotelMangementSystem.Service;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.patel.hotelMangementSystem.Exception.AadharCardNumberAlreadyExistException;
import com.patel.hotelMangementSystem.Exception.EmailIdAlreadyExistException;
import com.patel.hotelMangementSystem.Exception.PanCardNumberAlreadyExistException;
import com.patel.hotelMangementSystem.Exception.PhoneNumberAlreadyExist;
import com.patel.hotelMangementSystem.Model.Address;
import com.patel.hotelMangementSystem.Model.Customer;
import com.patel.hotelMangementSystem.Model.CustomerRoom;
import com.patel.hotelMangementSystem.Model.Room;
import com.patel.hotelMangementSystem.Repository.CustomerRepository;
import com.patel.hotelMangementSystem.Repository.CustomerRoomRepository;
import com.patel.hotelMangementSystem.Repository.RoomRepository;
import com.patel.hotelMangementSystem.Utility.ReflectionUtil;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private CustomerRoomRepository customerRoomRepository;

	ReflectionUtil refUtil = ReflectionUtil.getInstance();

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void setUp() {
		objectMapper.registerModule(new JavaTimeModule());
	}

	public Customer persistCustomer(Customer customer) {
		try {
			customer.setDeleteFlag(false);
			Customer customerToDB = customerRepository.save(customer);
			roomRepository.updatRoomAvailibility("NOT_AVAILABLE", customerToDB.getRoomID().getId());
			Long customerId = customerToDB.getId();
			Room room = roomRepository.getRoomById(customer.getRoomID().getId());
			CustomerRoom customerRoom = new CustomerRoom();

			customerRoom.setAadharCardNumber(customerToDB.getAadharCardNumber());
			customerRoom.setDeleteFlag(false);
			customerRoom.setAddress(customerToDB.getAddress());
			customerRoom.setPanCardNumber(customerToDB.getPanCardNumber());
			customerRoom.setPhoneNumber(customerToDB.getPhoneNumber());
			customerRoom.setEmail(customerToDB.getEmail());
			customerRoom.setRoomUniqueId(room.getRoomUniqueId());
			customerRoom.setHotelUniqueId(room.getHotelID().getHotelUniqueId());
			customerRoom.setBookedFrom(room.getBookedFrom());
			customerRoom.setBookedTo(room.getBookedTo());
			customerRoom.setStayedFrom(room.getReservedFrom());
			customerRoom.setStayedTo(room.getReservedTo());
			customerRoom.setTotalCost(room.getTotalCost());
			customerRoom.setAcNonAcType(room.getRoomColdness());
			customerRoom.setNoOfBeds(room.getNoOfBeds());
			customerRoomRepository.save(customerRoom);

			return customerToDB;
		} catch (AadharCardNumberAlreadyExistException ex) {
			throw new AadharCardNumberAlreadyExistException("Sorry Aadhar Card Number is Already In Use");
		} catch (PanCardNumberAlreadyExistException pex) {
			throw new PanCardNumberAlreadyExistException("Sorry Pan Card Number is Already In Use");
		} catch (PhoneNumberAlreadyExist pex) {
			throw new PhoneNumberAlreadyExist("Sorry PhoneNumber is Already In Use");
		} catch (EmailIdAlreadyExistException emailEx) {
			throw new EmailIdAlreadyExistException("Sorry Email Id Is Already In Use");
		}

	}

	public Customer getCustomerByEmailAndPhoneNumber(String aadharNumber, String phoneNumber)
			throws PhoneNumberAlreadyExist {
		Customer customer = customerRepository.getCustomerByAadharNumber(aadharNumber, phoneNumber);
		if (customer == null) {
			throw new AadharCardNumberAlreadyExistException(
					"Sorry No Data Found With aadharNumber:- " + aadharNumber + "and PhoneNumber:- " + phoneNumber);
		}
		return customer;
	}

	public List<Customer> getCustomerDetailsByAadharaCardNumber(String aadharCardNumber) {
		List<Customer> specificCustomerDetails = customerRepository.getCustomerDetailsByAadharNumber(aadharCardNumber);
		if (specificCustomerDetails == null || specificCustomerDetails.size() == 0) {
			throw new AadharCardNumberAlreadyExistException(
					"Sorry Customer Details Found For: -" + aadharCardNumber + " AadharCardNumber");
		}
		return specificCustomerDetails;
	}

	public List<Customer> getAllCustomer() {
		List<Customer> customerList = customerRepository.getAllCustomer();
		if (customerList == null || customerList.size() == 0) {
			throw new AadharCardNumberAlreadyExistException("Sorry No Date Found For Class Customer");
		}
		return customerList;
	}

	public String deleteCustomerByAadharAndPhoneNumber(String aadharNumber, String phoneNumber)
			throws PhoneNumberAlreadyExist {
		Customer customer = customerRepository.getCustomerByAadharNumber(aadharNumber, phoneNumber);
		if (customer == null) {
			throw new AadharCardNumberAlreadyExistException(
					"Sorry No Data Found for AadharCard :- " + aadharNumber + " and for PhoneNumber:- " + phoneNumber);

		}
		customerRepository.deleteCustomerByAadharAndPhone(aadharNumber, phoneNumber);
		return "SuccessFully Deleted";
	}

	public Customer updateCustomerDetails(String customer, String aadharCardNumber) {
		Customer customerFromDB = customerRepository.getCustomerByAadharNumber(aadharCardNumber);
		if (customer == null) {
			throw new AadharCardNumberAlreadyExistException(
					"Sorry No Data Found for AadharCard :- " + aadharCardNumber);

		}
		System.out.println(customerFromDB);
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(customer);
			for (Iterator iterator = ((Map<String, String>) obj).keySet().iterator(); iterator.hasNext();) {
				String propName = (String) iterator.next();

				if (propName.equals("address")) {
					if (obj.get("address") != null) {
						JSONObject addressObject = (JSONObject) obj.get("address");
						if (customerFromDB.getAddress() == null) {
							customerFromDB.setAddress(new Address());
						}
						for (Object src : addressObject.keySet()) {
							String props = (String) src;

							System.out.println(props + "----->" + addressObject.get(props));
							refUtil.getSetterMethod("Address", props).invoke(customerFromDB.getAddress(),
									addressObject.get(props));
						}

					} else {
						customerFromDB.setAddress(null);
					}
				} else {
					System.out.println(propName + "----->" + obj.get(propName));
					refUtil.getSetterMethod("Customer", propName).invoke(customerFromDB, obj.get(propName));
				}
			}

			Customer customerResponse = customerRepository.save(customerFromDB);
			return customerResponse;
		} catch (ParseException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new AadharCardNumberAlreadyExistException("Sorry Customer Details Could Not Be Updated");
		}

	}

}
