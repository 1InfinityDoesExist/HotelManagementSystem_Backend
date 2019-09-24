package com.patel.hotelMangementSystem.Model;

import java.io.Serializable;
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
@Table(name = "customer_room_table", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "aadhar_card_number", "pan_card_number", "email", "phone_number" }) })
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

}
