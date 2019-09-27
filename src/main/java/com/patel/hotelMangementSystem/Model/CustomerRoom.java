package com.patel.hotelMangementSystem.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "CustomerRoom")
@Table(name = "customer_room_table", uniqueConstraints = { @UniqueConstraint(columnNames = { "aadhar_card_number" }) })
public class CustomerRoom extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(notes = "Primary Key For The Customer Room Table")
	private Long id;

	@Column(name = "aadhar_card_number", updatable = false)
	@Size(min = 12, max = 12, message = "Aadhar Card Number must Be 12 Digit long")
	@ApiModelProperty(notes = "Aadhar Card Number")
	private Double aadharCardNumber;

	@Column(name = "pan_card_number", updatable = false)
	@Size(min = 9, max = 9, message = "Pan Card Number must be 9 character long")
	@ApiModelProperty(notes = "Pan Card Number")
	private String panCardNumber;

	@Column(name = "phone_number", updatable = true)
	@Size(min = 10, max = 10, message = "Phone Number must be 10 digit long")
	@ApiModelProperty(notes = "Phone Number of the person")
	private String phoneNumber;

	@Column(name = "email")
	@Email(message = "Email must be a valid email id")
	@ApiModelProperty(notes = "Email of the person")
	private String email;

	@Column(name = "address", columnDefinition = "jsonb")
	@Type(type = "AddressType")
	@ApiModelProperty(notes = "Address of the Customer")
	private Address address;

	@Column(name = "room_unique_id", updatable = false)
	@ApiModelProperty(notes = "Room Unique Id To Identity Each Room")
	private String roomUniqueId;

	@Column(name = "hotel_unique_id")
	@ApiModelProperty(notes = "Hotel Unique Id")
	private String hotelUniqueId;

	@Column(name = "booked_from")
	@ApiModelProperty(notes = "Booked From")
	private Date bookedFrom;

	@Column(name = "booked_to")
	@ApiModelProperty(notes = "Booked TO")
	private Date bookedTo;

	@Column(name = "stayed_from")
	@ApiModelProperty(notes = "Reserved From ")
	private Date stayedFrom;

	@Column(name = "stayed_to")
	@ApiModelProperty(notes = "Reserved TO")
	private Date stayedTo;

	@Column(name = "check_out_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(notes = "Check Out Date")
	private Date checkOutDate;

	@Column(name = "total_cost")
	@ApiModelProperty(notes = "Total Cost For Spending In The Hotel")
	private Long totalCost;

	@Column(name = "total_amount_pay_on_initially")
	@ApiModelProperty(notes = "TotalAmountPayOnInitially")
	private Long totalAmountPayOnInitially;

	@Column(name = "total_amount_pay_on_finally")
	@ApiModelProperty(notes = "TotalAmountPayOnInitially")
	private Long totalAmountPayOnFinally;

	@Column(name = "payment_type")
	@ApiModelProperty(notes = "Payment Types - Advance and FullPayment")
	private String paymentType;

	@Column(name = "status")
	@ApiModelProperty(notes = "Status Means CheckedIn/ CheckedOut")
	private String status;

	@Column(name = "ac_non_ac")
	@ApiModelProperty(notes = "Type Of Bed They Are Looking For")
	private String acNonAcType;
	@Column(name = "no_of_beds")
	@ApiModelProperty(notes = "No of Bed They Are Looking For")
	private Long noOfBeds;

	public CustomerRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerRoom(Long id, LocalDateTime creationDate, LocalDateTime modificationDate, Boolean deleteFlag,
			String notes) {
		super(id, creationDate, modificationDate, deleteFlag, notes);
		// TODO Auto-generated constructor stub
	}

	public CustomerRoom(Long id,
			@Size(min = 12, max = 12, message = "Aadhar Card Number must Be 12 Digit long") Double aadharCardNumber,
			@Size(min = 9, max = 9, message = "Pan Card Number must be 9 character long") String panCardNumber,
			@Size(min = 10, max = 10, message = "Phone Number must be 10 digit long") String phoneNumber,
			@Email(message = "Email must be a valid email id") String email, Address address, String roomUniqueId,
			String hotelUniqueId, Date bookedFrom, Date bookedTo, Date stayedFrom, Date stayedTo, Date checkOutDate,
			Long totalCost, Long totalAmountPayOnInitially, Long totalAmountPayOnFinally, String paymentType,
			String status, String acNonAcType, Long noOfBeds) {
		super();
		this.id = id;
		this.aadharCardNumber = aadharCardNumber;
		this.panCardNumber = panCardNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.roomUniqueId = roomUniqueId;
		this.hotelUniqueId = hotelUniqueId;
		this.bookedFrom = bookedFrom;
		this.bookedTo = bookedTo;
		this.stayedFrom = stayedFrom;
		this.stayedTo = stayedTo;
		this.checkOutDate = checkOutDate;
		this.totalCost = totalCost;
		this.totalAmountPayOnInitially = totalAmountPayOnInitially;
		this.totalAmountPayOnFinally = totalAmountPayOnFinally;
		this.paymentType = paymentType;
		this.status = status;
		this.acNonAcType = acNonAcType;
		this.noOfBeds = noOfBeds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAadharCardNumber() {
		return aadharCardNumber;
	}

	public void setAadharCardNumber(Double aadharCardNumber) {
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

	public String getRoomUniqueId() {
		return roomUniqueId;
	}

	public void setRoomUniqueId(String roomUniqueId) {
		this.roomUniqueId = roomUniqueId;
	}

	public String getHotelUniqueId() {
		return hotelUniqueId;
	}

	public void setHotelUniqueId(String hotelUniqueId) {
		this.hotelUniqueId = hotelUniqueId;
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

	public Date getStayedFrom() {
		return stayedFrom;
	}

	public void setStayedFrom(Date stayedFrom) {
		this.stayedFrom = stayedFrom;
	}

	public Date getStayedTo() {
		return stayedTo;
	}

	public void setStayedTo(Date stayedTo) {
		this.stayedTo = stayedTo;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAcNonAcType() {
		return acNonAcType;
	}

	public void setAcNonAcType(String acNonAcType) {
		this.acNonAcType = acNonAcType;
	}

	public Long getNoOfBeds() {
		return noOfBeds;
	}

	public void setNoOfBeds(Long noOfBeds) {
		this.noOfBeds = noOfBeds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((aadharCardNumber == null) ? 0 : aadharCardNumber.hashCode());
		result = prime * result + ((acNonAcType == null) ? 0 : acNonAcType.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((bookedFrom == null) ? 0 : bookedFrom.hashCode());
		result = prime * result + ((bookedTo == null) ? 0 : bookedTo.hashCode());
		result = prime * result + ((checkOutDate == null) ? 0 : checkOutDate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((hotelUniqueId == null) ? 0 : hotelUniqueId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((noOfBeds == null) ? 0 : noOfBeds.hashCode());
		result = prime * result + ((panCardNumber == null) ? 0 : panCardNumber.hashCode());
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((roomUniqueId == null) ? 0 : roomUniqueId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((stayedFrom == null) ? 0 : stayedFrom.hashCode());
		result = prime * result + ((stayedTo == null) ? 0 : stayedTo.hashCode());
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
		CustomerRoom other = (CustomerRoom) obj;
		if (aadharCardNumber == null) {
			if (other.aadharCardNumber != null)
				return false;
		} else if (!aadharCardNumber.equals(other.aadharCardNumber))
			return false;
		if (acNonAcType == null) {
			if (other.acNonAcType != null)
				return false;
		} else if (!acNonAcType.equals(other.acNonAcType))
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
		if (checkOutDate == null) {
			if (other.checkOutDate != null)
				return false;
		} else if (!checkOutDate.equals(other.checkOutDate))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (hotelUniqueId == null) {
			if (other.hotelUniqueId != null)
				return false;
		} else if (!hotelUniqueId.equals(other.hotelUniqueId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (noOfBeds == null) {
			if (other.noOfBeds != null)
				return false;
		} else if (!noOfBeds.equals(other.noOfBeds))
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
		if (roomUniqueId == null) {
			if (other.roomUniqueId != null)
				return false;
		} else if (!roomUniqueId.equals(other.roomUniqueId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (stayedFrom == null) {
			if (other.stayedFrom != null)
				return false;
		} else if (!stayedFrom.equals(other.stayedFrom))
			return false;
		if (stayedTo == null) {
			if (other.stayedTo != null)
				return false;
		} else if (!stayedTo.equals(other.stayedTo))
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
		return "CustomerRoom [id=" + id + ", aadharCardNumber=" + aadharCardNumber + ", panCardNumber=" + panCardNumber
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", roomUniqueId="
				+ roomUniqueId + ", hotelUniqueId=" + hotelUniqueId + ", bookedFrom=" + bookedFrom + ", bookedTo="
				+ bookedTo + ", stayedFrom=" + stayedFrom + ", stayedTo=" + stayedTo + ", checkOutDate=" + checkOutDate
				+ ", totalCost=" + totalCost + ", totalAmountPayOnInitially=" + totalAmountPayOnInitially
				+ ", totalAmountPayOnFinally=" + totalAmountPayOnFinally + ", paymentType=" + paymentType + ", status="
				+ status + ", acNonAcType=" + acNonAcType + ", noOfBeds=" + noOfBeds + "]";
	}

}
