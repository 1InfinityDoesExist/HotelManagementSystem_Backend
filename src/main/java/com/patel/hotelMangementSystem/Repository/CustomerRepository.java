package com.patel.hotelMangementSystem.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patel.hotelMangementSystem.Model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	@Query("Select Customer from #{#entityName} Customer where aadharCardNumber=?1 and phoneNumber=?2")
	public Customer getCustomerByAadharNumber(String aadharCardNumber, String phoneNumber);

	@Query("Select Customer from #{#entityName} Customer ")
	public List<Customer> getAllCustomer();

	@Modifying
	@Transactional
	@Query("Update #{#entityName} Customer  set deleteFlag = true where aadharCardNumber=?1 and phoneNumber=?2")
	public void deleteCustomerByAadharAndPhone(String aadharNumber, String phoneNumber);

	@Query("Select Customer from #{#entityName} Customer where aadharCardNumber=?1")
	public Customer getCustomerByAadharNumber(String aadharCardNumber);

	@Query("Select Customer from #{#entityName} Customer where aadharCardNumber=?1")
	public List<Customer> getCustomerDetailsByAadharNumber(String aadharCardNumber);

}
