package com.patel.hotelMangementSystem.Controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patel.hotelMangementSystem.Model.Customer;
import com.patel.hotelMangementSystem.Service.CustomerService;
import com.patel.hotelMangementSystem.Service.MapErrorsToFields;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/object/customer")
@Api(value = "CusomerRoomRest", description = "CustomerRestController Apis")
public class CustomerRestController {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private MapErrorsToFields mapErrorToField;

	@PostMapping(path = "/create")
	@ApiOperation(value = "To Persiste Customer Details To The Datatabase...!!!")
	public ResponseEntity<?> createCustomerDetails(@Valid @RequestBody Customer customer, BindingResult bindingResult) {

		ResponseEntity<?> errorMap = mapErrorToField.mapHotelError(bindingResult);

		Customer customerFromDB = customerService.persistCustomer(customer);
		return new ResponseEntity<Customer>(customerFromDB, HttpStatus.CREATED);
	}

	@GetMapping(path = "/get")
	@ApiOperation(value = "To Get Customer Details via Custeomer AadharCardNumber and Telephone")
	public ResponseEntity<?> getCustomerByAadharCardAndPhoneNumber(
			@RequestParam(value = "aadharCardNumber") String aadharCardNumber,
			@RequestParam(value = "phoneNumber") String phoneNumber) {

		Customer customer = customerService.getCustomerByEmailAndPhoneNumber(aadharCardNumber, phoneNumber);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping(path = "/get/all")
	@ApiOperation(value = "Get All Customer and Their Detials")
	public ResponseEntity<?> getAllCustomer() {
		List<Customer> customerList = customerService.getAllCustomer();
		return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete")
	@ApiOperation(value = "Delete Customer By AadharCardNumber and PhoneNubmer")
	public ResponseEntity<?> deleteCustomer(@RequestParam(value = "aadharCardNumber") String aadharCardNumber,
			@RequestParam(value = "phoneNumber") String phoneNumber) {
		String customer = customerService.deleteCustomerByAadharAndPhoneNumber(aadharCardNumber, phoneNumber);
		return new ResponseEntity<String>(customer, HttpStatus.OK);
	}

	@PatchMapping(path = "/update/{aadharCardNumber}")
	@ApiOperation(value = "Update Customer By Using AadharCardNumber")
	public ResponseEntity<?> updateCustomerByAadharCardNumber(
			@Valid @PathVariable(value = "aadharCardNumber") String aadharCardNumber, @RequestBody String customer) {

		Customer customerResponse = customerService.updateCustomerDetails(customer, aadharCardNumber);
		return new ResponseEntity<Customer>(customerResponse, HttpStatus.OK);

	}
}
