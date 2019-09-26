package com.patel.hotelMangementSystem.Exception;

public class AadharCardNumberAlreadyExistResponse {
	public String aadharCardNumber;

	public AadharCardNumberAlreadyExistResponse(String aadharCardNumber) {
		super();
		this.aadharCardNumber = aadharCardNumber;
	}

	public String getAadharCardNumber() {
		return aadharCardNumber;
	}

	public void setAadharCardNumber(String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}

}
