package com.patel.hotelMangementSystem.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patel.hotelMangementSystem.Model.Hotel;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {

}
