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

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "PanCard")
@Table(name = "pan_card", uniqueConstraints = { @UniqueConstraint(columnNames = { "pan_card_number" }) })
@Api(value = "PanCard Object", description = "PanCard Object")

public class PanCard implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(notes = "Primary Key of The Person")
	private Long id;

	@Column(name = "pan_card_number", insertable = true, updatable = false, nullable = false, unique = true)
	@ApiModelProperty(notes = "Pan Card of the Customer")
	private String panCardNumber;

	@Column(name = "creationDate")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date creationDate;

	public PanCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PanCard(Long id, String panCardNumber, Date creationDate) {
		super();
		this.id = id;
		this.panCardNumber = panCardNumber;
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPanCardNumber() {
		return panCardNumber;
	}

	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((panCardNumber == null) ? 0 : panCardNumber.hashCode());
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
		PanCard other = (PanCard) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
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
		return true;
	}

	@Override
	public String toString() {
		return "PanCard [id=" + id + ", panCardNumber=" + panCardNumber + ", creationDate=" + creationDate + "]";
	}

}
