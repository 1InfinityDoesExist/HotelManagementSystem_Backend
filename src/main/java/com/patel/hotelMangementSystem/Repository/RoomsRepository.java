package com.patel.hotelMangementSystem.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patel.hotelMangementSystem.Model.Rooms;

@Repository
public interface RoomsRepository extends CrudRepository<Rooms, Long> {

	@Query("Select Rooms from #{#entityName} Rooms where id=?1")
	public Rooms getRoomsById(Long id);

}
