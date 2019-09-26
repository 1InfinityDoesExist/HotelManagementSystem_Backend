package com.patel.hotelMangementSystem.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patel.hotelMangementSystem.Model.Hotel;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {

	@Query("Select Hotel from #{#entityName} Hotel where hotelUniqueId=?1 and deleteFlag=false")
	public Hotel getHotelByUniqueId(String uniqueId);

	@Query("SELECT Hotel from #{#entityName} Hotel where deleteFlag=false")
	public List<Hotel> getAllHotels();

	@Modifying
	@Transactional
	@Query("UPDATE #{#entityName} Hotel set deleteFlag=true where hotelUniqueId=?1")
	public void deleteHotel(String hotelUniqueId);

	@Query("Update #{#entityName} Hotel set hotelUniqueId=?1 where id=?2")
	@Modifying
	@Transactional
	public void updateHotelUniqueId(String uniqueIdForHotel, Long id);

}
