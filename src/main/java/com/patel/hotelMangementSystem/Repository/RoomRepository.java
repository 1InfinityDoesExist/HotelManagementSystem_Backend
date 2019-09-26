package com.patel.hotelMangementSystem.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patel.hotelMangementSystem.Model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

	@Query("Select Room from #{#entityName} Room where deleteFlag=false and roomUniqueId=?1")
	public Room getRoomByUniqueId(String roomUniqueId);

	@Query("Select Room from #{#entityName} Room where deleteFlag=false")
	public List<Room> getAllRooms();

	@Modifying
	@Transactional
	@Query("Update #{#entityName} Room set deleteFlag=true where roomUniqueId=?1")
	public void deleteRoomByUniqueId(String roomUniqueId);

	@Modifying
	@Transactional
	@Query("Update #{#entityName} Room set roomUniqueId=?1 where id=?2")
	public void updateRoomUniqueId(String roomUniqueId, Long id);

}
