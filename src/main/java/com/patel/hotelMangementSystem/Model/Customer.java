package com.patel.hotelMangementSystem.Model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Customer")
@Table(name = "customer_table", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "aadhar_card_number", "pan_card_number", "email", "phone_number" }) })

@EntityListeners(AuditingEntityListener.class)
@TypeDefs({ @TypeDef(name = "AddressType", typeClass = AddressType.class) })
@Api(value = "Customer Class", description = "Customer Class")
public class Customer extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer")
	@ApiModelProperty(notes = "Customer Primary Key")
	private Long id;

	@Column(name = "aadhar_card_number", updatable = false, unique = true, insertable = true)
	@Size(min = 12, max = 12, message = "Pan Card Number must be 9 character long")
	@ApiModelProperty(notes = "Aadhar Card Number")
	@NotBlank(message = "AadharCardNumber is Mandatory")
	private String aadharCardNumber;

	@Column(name = "pan_card_number", insertable = true, updatable = false, unique = true)
	@Size(min = 9, max = 9, message = "Pan Card Number must be 9 character long")
	@ApiModelProperty(notes = "Pan Card Number")
	@NotBlank(message = "Pan Card Number must not be Blank")
	private String panCardNumber;

	@Column(name = "phone_number", insertable = true, updatable = true, unique = true)
	@Size(min = 10, max = 10, message = "Phone Number must be 10 digit long")
	@ApiModelProperty(notes = "Phone Number of the person")
	@NotBlank(message = "PhoneNumber is Mandatory")
	private String phoneNumber;

	@Column(name = "email", insertable = true, updatable = true, unique = true)
	@Email(message = "Email must be a valid email id")
	@ApiModelProperty(notes = "Email of the person")
	@NotBlank(message = "Email id Field is Mandatory")
	private String email;

	@Column(name = "address", columnDefinition = "jsonb")
	@Type(type = "AddressType")
	@ApiModelProperty(notes = "Address of the Customer")
	private Address address;

	@Column(name = "status")
	@ApiModelProperty(notes = "Status Of The Customer")
	private String status;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "room_id", columnDefinition = "bigint", nullable = false, updatable = true, referencedColumnName = "id")
	@JsonIgnoreProperties("customer_id")
	private Room roomID;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id, LocalDateTime creationDate, LocalDateTime modificationDate, Boolean deleteFlag,
			String notes) {
		super(id, creationDate, modificationDate, deleteFlag, notes);
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id,
			@Size(min = 12, max = 12, message = "Pan Card Number must be 9 character long") String aadharCardNumber,
			@Size(min = 9, max = 9, message = "Pan Card Number must be 9 character long") @NotBlank(message = "Pan Card Number must not be Blank") String panCardNumber,
			@Size(min = 10, max = 10, message = "Phone Number must be 10 digit long") @NotBlank(message = "PhoneNumber is Mandatory") String phoneNumber,
			@Email(message = "Email must be a valid email id") @NotBlank(message = "Email id Field is Mandatory") String email,
			Address address, String status) {
		super();
		this.id = id;
		this.aadharCardNumber = aadharCardNumber;
		this.panCardNumber = panCardNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.status = status;
	}

	public Room getRoomID() {
		return roomID;
	}

	public void setRoomID(Room roomID) {
		this.roomID = roomID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAadharCardNumber() {
		return aadharCardNumber;
	}

	public void setAadharCardNumber(String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}

	public String getPanCardNumber() {
		return panCardNumber;
	}

	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((aadharCardNumber == null) ? 0 : aadharCardNumber.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((panCardNumber == null) ? 0 : panCardNumber.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Customer other = (Customer) obj;
		if (aadharCardNumber == null) {
			if (other.aadharCardNumber != null)
				return false;
		} else if (!aadharCardNumber.equals(other.aadharCardNumber))
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (panCardNumber == null) {
			if (other.panCardNumber != null)
				return false;
		} else if (!panCardNumber.equals(other.panCardNumber))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", aadharCardNumber=" + aadharCardNumber + ", panCardNumber=" + panCardNumber
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", status=" + status
				+ "]";
	}

}
