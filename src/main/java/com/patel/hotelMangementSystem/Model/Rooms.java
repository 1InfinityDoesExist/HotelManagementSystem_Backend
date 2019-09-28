package com.patel.hotelMangementSystem.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "Rooms")
@Table(name = "rooms_table")
public class Rooms implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String roomName;
	private String roomColor;
	private Long roomLength;
	private Double cost;
	private String notes;
	private String status;

	@ManyToOne(cascade= {CascadeType.MERGE})
	@JoinColumn(name = "house_id", columnDefinition = "bigint", referencedColumnName = "id", nullable = false)
	@JsonIgnoreProperties("rooms")
	private House house_id;

	public Rooms() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rooms(Long id, String roomName, String roomColor, Long roomLength, Double cost, String notes,
			String status) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.roomColor = roomColor;
		this.roomLength = roomLength;
		this.cost = cost;
		this.notes = notes;
		this.status = status;
	}

	public House getHouse_id() {
		return house_id;
	}

	public void setHouse_id(House house_id) {
		System.out.println("hi");
		this.house_id = house_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomColor() {
		return roomColor;
	}

	public void setRoomColor(String roomColor) {
		this.roomColor = roomColor;
	}

	public Long getRoomLength() {
		return roomLength;
	}

	public void setRoomLength(Long roomLength) {
		this.roomLength = roomLength;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
		int result = 1;
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((roomColor == null) ? 0 : roomColor.hashCode());
		result = prime * result + ((roomLength == null) ? 0 : roomLength.hashCode());
		result = prime * result + ((roomName == null) ? 0 : roomName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Rooms other = (Rooms) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (roomColor == null) {
			if (other.roomColor != null)
				return false;
		} else if (!roomColor.equals(other.roomColor))
			return false;
		if (roomLength == null) {
			if (other.roomLength != null)
				return false;
		} else if (!roomLength.equals(other.roomLength))
			return false;
		if (roomName == null) {
			if (other.roomName != null)
				return false;
		} else if (!roomName.equals(other.roomName))
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
		return "Rooms [id=" + id + ", roomName=" + roomName + ", roomColor=" + roomColor + ", roomLength=" + roomLength
				+ ", cost=" + cost + ", notes=" + notes + ", status=" + status + "]";
	}

}
