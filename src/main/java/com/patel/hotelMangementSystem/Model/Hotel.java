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

@Entity(name = "Hotel")
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
	@Size(min = 6, max = 12, message = "Unique Id Must Not Exceed There Values , Should Be of Length 6")
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

	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hotel(@NotBlank(message = "Please Do Provide A Name To The Hotel") String name, Address address,
			@Size(min = 6, max = 6, message = "Unique Id Must Not Exceed There Values , Should Be of Length 6") String hotelUniqueId,
			String hotelOwner, String hotelEmail, String hotelContactNumber) {
		super();
		this.name = name;
		this.address = address;
		this.hotelUniqueId = hotelUniqueId;
		this.hotelOwner = hotelOwner;
		this.hotelEmail = hotelEmail;
		this.hotelContactNumber = hotelContactNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getHotelUniqueId() {
		return hotelUniqueId;
	}

	public void setHotelUniqueId(String hotelUniqueId) {
		this.hotelUniqueId = hotelUniqueId;
	}

	public String getHotelOwner() {
		return hotelOwner;
	}

	public void setHotelOwner(String hotelOwner) {
		this.hotelOwner = hotelOwner;
	}

	public String getHotelEmail() {
		return hotelEmail;
	}

	public void setHotelEmail(String hotelEmail) {
		this.hotelEmail = hotelEmail;
	}

	public String getHotelContactNumber() {
		return hotelContactNumber;
	}

	public void setHotelContactNumber(String hotelContactNumber) {
		this.hotelContactNumber = hotelContactNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((hotelContactNumber == null) ? 0 : hotelContactNumber.hashCode());
		result = prime * result + ((hotelEmail == null) ? 0 : hotelEmail.hashCode());
		result = prime * result + ((hotelOwner == null) ? 0 : hotelOwner.hashCode());
		result = prime * result + ((hotelUniqueId == null) ? 0 : hotelUniqueId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (hotelContactNumber == null) {
			if (other.hotelContactNumber != null)
				return false;
		} else if (!hotelContactNumber.equals(other.hotelContactNumber))
			return false;
		if (hotelEmail == null) {
			if (other.hotelEmail != null)
				return false;
		} else if (!hotelEmail.equals(other.hotelEmail))
			return false;
		if (hotelOwner == null) {
			if (other.hotelOwner != null)
				return false;
		} else if (!hotelOwner.equals(other.hotelOwner))
			return false;
		if (hotelUniqueId == null) {
			if (other.hotelUniqueId != null)
				return false;
		} else if (!hotelUniqueId.equals(other.hotelUniqueId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Hotel [name=" + name + ", address=" + address + ", hotelUniqueId=" + hotelUniqueId + ", hotelOwner="
				+ hotelOwner + ", hotelEmail=" + hotelEmail + ", hotelContactNumber=" + hotelContactNumber + "]";
	}

}
