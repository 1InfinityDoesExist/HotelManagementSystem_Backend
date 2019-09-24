package com.patel.hotelMangementSystem.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patel.hotelMangementSystem.Model.CustomerRoom;

@Repository
public interface CustomerRoomRepository extends CrudRepository<CustomerRoom, Long> {

}
