package com.patel.hotelMangementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patel.hotelMangementSystem.Model.AadharCard;

@Repository
public interface AadharCardRepository extends CrudRepository<AadharCard, Long> {

	@Query("Select AadharCard from #{#entityName} AadharCard where id=?1")
	public AadharCard getAadharCardByID(Long id);

	@Query("Select AadharCard from #{#entityName} AadharCard")
	public List<AadharCard> getAllAadhar();

}
