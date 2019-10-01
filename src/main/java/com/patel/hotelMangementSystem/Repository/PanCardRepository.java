package com.patel.hotelMangementSystem.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.patel.hotelMangementSystem.Model.AadharCard;

@Repository
public interface PanCardRepository extends CrudRepository<AadharCard, Long> {

}
