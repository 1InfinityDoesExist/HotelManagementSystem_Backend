package com.patel.hotelMangementSystem.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "House")
@Table(name = "house")
public class House implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String houseAddress;
	private String houseCity;
	private String houseCountry;
	private Long houseNumber;
	private String houseStory;
	private String houseStatus;

	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "house_id")
	@JsonIgnoreProperties("house_id")
	private List<Rooms> rooms = new ArrayList<Rooms>();

	public House() {
		super();
		// TODO Auto-generated constructor stub
	}

	public House(Long id, String houseAddress, String houseCity, String houseCountry, Long houseNumber,
			String houseStory, String houseStatus) {
		super();
		this.id = id;
		this.houseAddress = houseAddress;
		this.houseCity = houseCity;
		this.houseCountry = houseCountry;
		this.houseNumber = houseNumber;
		this.houseStory = houseStory;
		this.houseStatus = houseStatus;
	}

	public List<Rooms> getRooms() {
		return rooms;
	}

	public void setRooms(List<Rooms> rooms) {
		this.rooms = rooms;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getHouseCity() {
		return houseCity;
	}

	public void setHouseCity(String houseCity) {
		this.houseCity = houseCity;
	}

	public String getHouseCountry() {
		return houseCountry;
	}

	public void setHouseCountry(String houseCountry) {
		this.houseCountry = houseCountry;
	}

	public Long getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Long houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getHouseStory() {
		return houseStory;
	}

	public void setHouseStory(String houseStory) {
		this.houseStory = houseStory;
	}

	public String getHouseStatus() {
		return houseStatus;
	}

	public void setHouseStatus(String houseStatus) {
		this.houseStatus = houseStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((houseAddress == null) ? 0 : houseAddress.hashCode());
		result = prime * result + ((houseCity == null) ? 0 : houseCity.hashCode());
		result = prime * result + ((houseCountry == null) ? 0 : houseCountry.hashCode());
		result = prime * result + ((houseNumber == null) ? 0 : houseNumber.hashCode());
		result = prime * result + ((houseStatus == null) ? 0 : houseStatus.hashCode());
		result = prime * result + ((houseStory == null) ? 0 : houseStory.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		House other = (House) obj;
		if (houseAddress == null) {
			if (other.houseAddress != null)
				return false;
		} else if (!houseAddress.equals(other.houseAddress))
			return false;
		if (houseCity == null) {
			if (other.houseCity != null)
				return false;
		} else if (!houseCity.equals(other.houseCity))
			return false;
		if (houseCountry == null) {
			if (other.houseCountry != null)
				return false;
		} else if (!houseCountry.equals(other.houseCountry))
			return false;
		if (houseNumber == null) {
			if (other.houseNumber != null)
				return false;
		} else if (!houseNumber.equals(other.houseNumber))
			return false;
		if (houseStatus == null) {
			if (other.houseStatus != null)
				return false;
		} else if (!houseStatus.equals(other.houseStatus))
			return false;
		if (houseStory == null) {
			if (other.houseStory != null)
				return false;
		} else if (!houseStory.equals(other.houseStory))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "House [id=" + id + ", houseAddress=" + houseAddress + ", houseCity=" + houseCity + ", houseCountry="
				+ houseCountry + ", houseNumber=" + houseNumber + ", houseStory=" + houseStory + ", houseStatus="
				+ houseStatus + "]";
	}

}
