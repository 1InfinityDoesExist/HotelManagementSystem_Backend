package com.patel.hotelMangementSystem.Model;

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
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Customer Class")
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
	private String Status;

}
