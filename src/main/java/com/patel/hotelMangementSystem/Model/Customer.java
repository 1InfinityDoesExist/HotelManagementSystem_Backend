package com.patel.hotelMangementSystem.Model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

	@Column(name = "aadhar_card_number", updatable = false)
	@Size(min = 12, max = 12, message = "Pan Card Number must be 9 character long")
	@ApiModelProperty(notes = "Aadhar Card Number")
	// @NotBlank(message = "AadharCardNumber is Mandatory")
	private String aadharCardNumber;

	@Column(name = "pan_card_number", updatable = false)
	@Size(min = 9, max = 9, message = "Pan Card Number must be 9 character long")
	@ApiModelProperty(notes = "Pan Card Number")
	@NotBlank(message = "Pan Card Number must not be Blank")
	private String panCardNumber;

	@Column(name = "phone_number", updatable = true)
	@Size(min = 10, max = 10, message = "Phone Number must be 10 digit long")
	@ApiModelProperty(notes = "Phone Number of the person")
	@NotBlank(message = "PhoneNumber is Mandatory")
	private String phoneNumber;

	@Column(name = "email")
	@Email(message = "Email must be a valid email id")
	@ApiModelProperty(notes = "Email of the person")
	@NotBlank(message = "Email id Field is Mandatory")
	private String email;

	@Column(name = "address", columnDefinition = "jsonb")
	@Type(type = "AddressType")
	@ApiModelProperty(notes = "Address of the Customer")
	private Address address;

	@Column(name = "payment_type")
	@ApiModelProperty(notes = "Payment Types - Advance and FullPayment")
	private String paymentType;

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

	@Column(name = "total_amount_pay_on_initially")
	@ApiModelProperty(notes = "TotalAmountPayOnInitially")
	private Long totalAmountPayOnInitially;

	@Column(name = "total_amount_pay_on_finally")
	@ApiModelProperty(notes = "TotalAmountPayOnInitially")
	private Long totalAmountPayOnFinally;

	@Column(name = "status")
	@ApiModelProperty(notes = "Status Of The Customer")
	private String status;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id, LocalDateTime creationDate, LocalDateTime modificationDate, Boolean deleteFlag,
			String notes) {
		super(id, creationDate, modificationDate, deleteFlag, notes);
		// TODO Auto-generated constructor stub
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Date getBookedFrom() {
		return bookedFrom;
	}

	public void setBookedFrom(Date bookedFrom) {
		this.bookedFrom = bookedFrom;
	}

	public Date getBookedTo() {
		return bookedTo;
	}

	public void setBookedTo(Date bookedTo) {
		this.bookedTo = bookedTo;
	}

	public Date getReservedFrom() {
		return reservedFrom;
	}

	public void setReservedFrom(Date reservedFrom) {
		this.reservedFrom = reservedFrom;
	}

	public Date getReservedTo() {
		return reservedTo;
	}

	public void setReservedTo(Date reservedTo) {
		this.reservedTo = reservedTo;
	}

	public Long getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Long totalCost) {
		this.totalCost = totalCost;
	}

	public Long getTotalAmountPayOnInitially() {
		return totalAmountPayOnInitially;
	}

	public void setTotalAmountPayOnInitially(Long totalAmountPayOnInitially) {
		this.totalAmountPayOnInitially = totalAmountPayOnInitially;
	}

	public Long getTotalAmountPayOnFinally() {
		return totalAmountPayOnFinally;
	}

	public void setTotalAmountPayOnFinally(Long totalAmountPayOnFinally) {
		this.totalAmountPayOnFinally = totalAmountPayOnFinally;
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
		result = prime * result + ((bookedFrom == null) ? 0 : bookedFrom.hashCode());
		result = prime * result + ((bookedTo == null) ? 0 : bookedTo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((panCardNumber == null) ? 0 : panCardNumber.hashCode());
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((reservedFrom == null) ? 0 : reservedFrom.hashCode());
		result = prime * result + ((reservedTo == null) ? 0 : reservedTo.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((totalAmountPayOnFinally == null) ? 0 : totalAmountPayOnFinally.hashCode());
		result = prime * result + ((totalAmountPayOnInitially == null) ? 0 : totalAmountPayOnInitially.hashCode());
		result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
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
		if (bookedFrom == null) {
			if (other.bookedFrom != null)
				return false;
		} else if (!bookedFrom.equals(other.bookedFrom))
			return false;
		if (bookedTo == null) {
			if (other.bookedTo != null)
				return false;
		} else if (!bookedTo.equals(other.bookedTo))
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
		if (paymentType == null) {
			if (other.paymentType != null)
				return false;
		} else if (!paymentType.equals(other.paymentType))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (reservedFrom == null) {
			if (other.reservedFrom != null)
				return false;
		} else if (!reservedFrom.equals(other.reservedFrom))
			return false;
		if (reservedTo == null) {
			if (other.reservedTo != null)
				return false;
		} else if (!reservedTo.equals(other.reservedTo))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (totalAmountPayOnFinally == null) {
			if (other.totalAmountPayOnFinally != null)
				return false;
		} else if (!totalAmountPayOnFinally.equals(other.totalAmountPayOnFinally))
			return false;
		if (totalAmountPayOnInitially == null) {
			if (other.totalAmountPayOnInitially != null)
				return false;
		} else if (!totalAmountPayOnInitially.equals(other.totalAmountPayOnInitially))
			return false;
		if (totalCost == null) {
			if (other.totalCost != null)
				return false;
		} else if (!totalCost.equals(other.totalCost))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", aadharCardNumber=" + aadharCardNumber + ", panCardNumber=" + panCardNumber
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", paymentType="
				+ paymentType + ", bookedFrom=" + bookedFrom + ", bookedTo=" + bookedTo + ", reservedFrom="
				+ reservedFrom + ", reservedTo=" + reservedTo + ", totalCost=" + totalCost
				+ ", totalAmountPayOnInitially=" + totalAmountPayOnInitially + ", totalAmountPayOnFinally="
				+ totalAmountPayOnFinally + ", status=" + status + "]";
	}

}
