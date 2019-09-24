package com.patel.hotelMangementSystem.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patel.hotelMangementSystem.Model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
