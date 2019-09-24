package com.patel.hotelMangementSystem.Exception;

public class HotelUniqueIdExceptionResponse {

	private String hotelUniqueId;

	public HotelUniqueIdExceptionResponse(String hotelUniqueId) {
		super();
		this.hotelUniqueId = hotelUniqueId;
	}

	public String getHotelUniqueId() {
		return hotelUniqueId;
	}

	public void setHotelUniqueId(String hotelUniqueId) {
		this.hotelUniqueId = hotelUniqueId;
	}

}
