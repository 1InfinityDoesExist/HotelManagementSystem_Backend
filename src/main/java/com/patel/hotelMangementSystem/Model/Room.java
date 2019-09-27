package com.patel.hotelMangementSystem.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Room")
@Table(name = "room", uniqueConstraints = { @UniqueConstraint(columnNames = { "room_unique_id" }) })
public class Room extends BaseEntity implements Serializable {

	@Column(name = "room_unique_id", updatable = false, unique = true)
	@ApiModelProperty(notes = "Room Unique Id To Identity Each Room")
	private String roomUniqueId;

	@Column(name = "status")
	@ApiModelProperty(notes = "Status of the Room")
	private String status;

	@Column(name = "booked_from")
	@ApiModelProperty(notes = "Booked From")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date bookedFrom;

	@Column(name = "booked_to")
	@ApiModelProperty(notes = "Booked TO")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date bookedTo;

	@Column(name = "reserved_from")
	@ApiModelProperty(notes = "Reserved From")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date reservedFrom;

	@Column(name = "reserved_to")
	@ApiModelProperty(notes = "Reserved TO")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date reservedTo;

	@Column(name = "total_cost")
	@ApiModelProperty(notes = "Total Cost")
	private Long totalCost;

	@Column(name = "room_coldness")
	@ApiModelProperty(notes = "Wheter The Room Is A/C or Non A/C")
	@NotBlank(message = "Ac / Non Ac Must Be Selected Mandatory Field")
	private String roomColdness;

	@Column(name = "no_of_beds")
	@ApiModelProperty(notes = "No of Beds In a Room")
	@Min(value = 1, message = "Min Number of beds must be 1")
	@Max(value = 2, message = "Max Number of bes must be 2")
	private Long noOfBeds;

	@Column(name = "room_condition_status")
	@NotBlank(message = "Room Condition Status Cannot be blank")
	private String roomConditonStatus;

	@Column(name = "hotel_name")
	@ApiModelProperty(name = "Hotel Name")
	private String hotelName;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "hotelUniqueId", nullable = false, updatable = false)
	private Hotel hotelRoom;

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Room(Long id, LocalDateTime creationDate, LocalDateTime modificationDate, Boolean deleteFlag, String notes) {
		super(id, creationDate, modificationDate, deleteFlag, notes);
		// TODO Auto-generated constructor stub
	}

	public Room(String roomUniqueId, String status, Date bookedFrom, Date bookedTo, Date reservedFrom, Date reservedTo,
			Long totalCost, @NotBlank(message = "Ac / Non Ac Must Be Selected Mandatory Field") String roomColdness,
			@Min(value = 1, message = "Min Number of beds must be 1") @Max(value = 2, message = "Max Number of bes must be 2") Long noOfBeds,
			@NotBlank(message = "Room Condition Status Cannot be blank") String roomConditonStatus, String hotelName) {
		super();
		this.roomUniqueId = roomUniqueId;
		this.status = status;
		this.bookedFrom = bookedFrom;
		this.bookedTo = bookedTo;
		this.reservedFrom = reservedFrom;
		this.reservedTo = reservedTo;
		this.totalCost = totalCost;
		this.roomColdness = roomColdness;
		this.noOfBeds = noOfBeds;
		this.roomConditonStatus = roomConditonStatus;
		this.hotelName = hotelName;
	}

	@JsonIgnore
	public Hotel getHotelRoom() {
		return hotelRoom;
	}

	public void setHotelRoom(Hotel hotelRoom) {
		this.hotelRoom = hotelRoom;
	}

	public String getRoomUniqueId() {
		return roomUniqueId;
	}

	public void setRoomUniqueId(String roomUniqueId) {
		this.roomUniqueId = roomUniqueId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getRoomColdness() {
		return roomColdness;
	}

	public void setRoomColdness(String roomColdness) {
		this.roomColdness = roomColdness;
	}

	public Long getNoOfBeds() {
		return noOfBeds;
	}

	public void setNoOfBeds(Long noOfBeds) {
		this.noOfBeds = noOfBeds;
	}

	public String getRoomConditonStatus() {
		return roomConditonStatus;
	}

	public void setRoomConditonStatus(String roomConditonStatus) {
		this.roomConditonStatus = roomConditonStatus;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bookedFrom == null) ? 0 : bookedFrom.hashCode());
		result = prime * result + ((bookedTo == null) ? 0 : bookedTo.hashCode());
		result = prime * result + ((hotelName == null) ? 0 : hotelName.hashCode());
		result = prime * result + ((noOfBeds == null) ? 0 : noOfBeds.hashCode());
		result = prime * result + ((reservedFrom == null) ? 0 : reservedFrom.hashCode());
		result = prime * result + ((reservedTo == null) ? 0 : reservedTo.hashCode());
		result = prime * result + ((roomColdness == null) ? 0 : roomColdness.hashCode());
		result = prime * result + ((roomConditonStatus == null) ? 0 : roomConditonStatus.hashCode());
		result = prime * result + ((roomUniqueId == null) ? 0 : roomUniqueId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Room other = (Room) obj;
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
		if (hotelName == null) {
			if (other.hotelName != null)
				return false;
		} else if (!hotelName.equals(other.hotelName))
			return false;
		if (noOfBeds == null) {
			if (other.noOfBeds != null)
				return false;
		} else if (!noOfBeds.equals(other.noOfBeds))
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
		if (roomColdness == null) {
			if (other.roomColdness != null)
				return false;
		} else if (!roomColdness.equals(other.roomColdness))
			return false;
		if (roomConditonStatus == null) {
			if (other.roomConditonStatus != null)
				return false;
		} else if (!roomConditonStatus.equals(other.roomConditonStatus))
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
		if (totalCost == null) {
			if (other.totalCost != null)
				return false;
		} else if (!totalCost.equals(other.totalCost))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [roomUniqueId=" + roomUniqueId + ", status=" + status + ", bookedFrom=" + bookedFrom
				+ ", bookedTo=" + bookedTo + ", reservedFrom=" + reservedFrom + ", reservedTo=" + reservedTo
				+ ", totalCost=" + totalCost + ", roomColdness=" + roomColdness + ", noOfBeds=" + noOfBeds
				+ ", roomConditonStatus=" + roomConditonStatus + ", hotelName=" + hotelName + "]";
	}

}
