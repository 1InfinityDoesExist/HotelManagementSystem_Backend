package com.patel.hotelMangementSystem.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Hotel Class")
@Table(name = "hotel", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "hotel_unique_id" }) })
@Api(value = "Hotel Clas", description = "This is the hotel class")
@EntityListeners(AuditingEntityListener.class)
@TypeDefs({ @TypeDef(name = "AddressType", typeClass = AddressType.class) })
public class Hotel extends BaseEntity implements Serializable {

	@Column(name = "name", updatable = true)
	@NotBlank(message = "Please Do Provide A Name To The Hotel")
	@ApiModelProperty(notes = "Name of the Hotel")
	private String name;

	@Column(name = "address", columnDefinition = "jsonb")
	@ApiModelProperty(notes = "Address Of the Hotel")
	@Type(type = "AddressType")
	private Address address;

	@Column(name = "hotel_unique_id", updatable = false)
	@Size(min = 6, max = 6, message = "Unique Id Must Not Exceed There Values , Should Be of Length 6")
	@ApiModelProperty(notes = "Unique Id given to each hotel")
	private String hotelUniqueId;

	@Column(name = "hotel_owner_name")
	@ApiModelProperty(notes = "Hotel owner Name")
	private String hotelOwner;

	@Column(name = "hotel_email")
	@ApiModelProperty(notes = "Email Address of The Email")
	private String hotelEmail;

	@Column(name = "hotel_contact_number")
	@ApiModelProperty(notes = "Contact Number of Hotel")
	private String hotelContactNumber;
	
	

}
