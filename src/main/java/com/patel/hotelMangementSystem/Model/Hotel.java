package com.patel.hotelMangementSystem.Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.Api;

@Entity(name = "Hotel Class")
@Table(name = "hotel")
@Api(value = "Hotel Clas", description = "This is the hotel class")
public class Hotel extends BaseEntity implements Serializable {
	

}
