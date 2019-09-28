package com.patel.hotelMangementSystem.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patel.hotelMangementSystem.Model.House;

@Repository
public interface HouseRepository extends CrudRepository<House, Long> {

	@Query("Select House from #{#entityName} House where id=?1")
	public House getHouseByID(Long id);

}
