package com.patel.hotelMangementSystem.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Room")
@Table(name = "room", uniqueConstraints = { @UniqueConstraint(columnNames = { "hotel_unique_id", "room_unique_id" }) })
public class Room extends BaseEntity implements Serializable {

	@Column(name = "room_unique_id", updatable = false)
	@ApiModelProperty(notes = "Room Unique Id To Identity Each Room")
	private String roomUniqueId;

	@Column(name = "status")
	@NotBlank(message = "Status Field Cant Be Blank")
	@ApiModelProperty(notes = "Status of the Room")
	private String status;

	@Column(name = "hotel_unique_id")
	@ApiModelProperty(notes = "Hotel Unique Id")
	private String hotelUniqueId;

	@Column(name = "booked_from")
	@ApiModelProperty(notes = "Booked From")
	private Date bookedFrom;

	@Column(name = "booked_to")
	@ApiModelProperty(notes = "Booked TO")
	private Date bookedTo;

	@Column(name = "reserved_from")
	@ApiModelProperty(notes = "Reserved From ")
	private Date reservedFrom;

	@Column(name = "reserved_to")
	@ApiModelProperty(notes = "Reserved TO")
	private Date reservedTo;

	@Column(name = "total_cost")
	@ApiModelProperty(notes = "Total Cost")
	private Long totalCost;

	@Column(name = "room_coldness")
	@ApiModelProperty(notes = "Wheter The Room Is A/C or Non A/C")
	private String roomColdness;

	@Column(name = "no_of_beds")
	@ApiModelProperty(notes = "No of Beds In a Room")
	private Long noOfBeds;
}
