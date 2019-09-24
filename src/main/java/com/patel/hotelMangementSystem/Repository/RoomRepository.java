package com.patel.hotelMangementSystem.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patel.hotelMangementSystem.Model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

}
