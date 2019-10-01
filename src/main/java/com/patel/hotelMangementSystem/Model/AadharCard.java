package com.patel.hotelMangementSystem.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "AadharCard")
@Table(name = "aadhar_card", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "aadhar_number", "email", "phone_number" }) })
@EntityListeners(AuditingEntityListener.class)
@TypeDefs({ @TypeDef(name = "AddressType", typeClass = AddressType.class) })
@Api(value = "AadharCard", description = "Aadhar Card Class")
public class AadharCard implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(notes = "Primary Key For AadharCard")
	private Long id;

	@Column(name = "first_name", insertable = true, updatable = true, nullable = false, unique = false)
	@NotBlank(message = "First Name is Mandatory")
	@ApiModelProperty(notes = "First Name of the Customer")
	private String firstName;

	@Column(name = "last_name", insertable = true, updatable = true, nullable = false, unique = false)
	@NotBlank(message = "Second Name is Mandatory")
	@ApiModelProperty(notes = "Last Name of the Customer")
	private String lastName;

	@Column(name = "phone_number", insertable = true, updatable = true, nullable = false, unique = true)
	@NotBlank(message = "Phone Number Cann't Be Blank")
	@ApiModelProperty(notes = "Phone Number of the Customer")
	private String phoneNumber;

	@Column(name = "email", insertable = true, updatable = true, nullable = true, unique = true)
	@NotBlank(message = "Email of the Customer")
	@ApiModelProperty(notes = "Email of the Customer")
	private String email;

	@Column(name = "address", columnDefinition = "jsonb", insertable = true, nullable = false, updatable = true, unique = false)
	@Type(type = "AddressType")
	// @NotBlank(message = "Address of the Customer")
	@ApiModelProperty(notes = "Address of the Customer")
	private Address address;

	@Column(name = "aadhar_number", updatable = false, nullable = false, unique = true)
	@ApiModelProperty(notes = "AadharCard Number of the Customer Which will be uniquely Generated")
	@NotBlank(message = "AadharCard Number Can't be Blank")
	@NotNull(message = "AadharCard Number Can't be Null")
	@NotEmpty(message = "AadharCard Number Can't be Empty")
	private String aadharNumber;

	public AadharCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AadharCard(Long id, @NotBlank(message = "First Name is Mandatory") String firstName,
			@NotBlank(message = "Second Name is Mandatory") String lastName,
			@NotBlank(message = "Phone Number Cann't Be Blank") String phoneNumber,
			@NotBlank(message = "Email of the Customer") String email,
			@NotBlank(message = "Address of the Customer") Address address, String aadharNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.aadharNumber = aadharNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aadharNumber == null) ? 0 : aadharNumber.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AadharCard other = (AadharCard) obj;
		if (aadharNumber == null) {
			if (other.aadharNumber != null)
				return false;
		} else if (!aadharNumber.equals(other.aadharNumber))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AadharCard [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", address=" + address + ", aadharNumber=" + aadharNumber + "]";
	}

}
