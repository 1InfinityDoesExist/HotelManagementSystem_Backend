package com.patel.hotelMangementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patel.hotelMangementSystem.Model.PanCard;

@Repository
public interface PanCardRepository extends CrudRepository<PanCard, Long> {

	@Query("Select PanCard from #{#entityName} PanCard where id = ?1")
	public PanCard getPanCardByID(Long id);

	@Query("Select PanCard From #{#entityName} PanCard")
	public List<PanCard> getAllPanCard();

}
