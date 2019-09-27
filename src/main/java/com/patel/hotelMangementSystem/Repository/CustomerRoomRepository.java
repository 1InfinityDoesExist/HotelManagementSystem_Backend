package com.patel.hotelMangementSystem.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patel.hotelMangementSystem.Model.CustomerRoom;

@Repository
public interface CustomerRoomRepository extends CrudRepository<CustomerRoom, Long> {

	@Query("Select CustomerRoom from #{#entityName} CustomerRoom where aadharCardNumber=?1 and checkOutDate=?2")
	public CustomerRoom getCustomerByTodayDate(String status, String aadharCardNumber, Date date);

	@Query("Select CustomerRoom from #{#entityName} CustomerRoom where status=?1")
	public List<CustomerRoom> getAllCustomerDetailsByStatus(String status);

}
