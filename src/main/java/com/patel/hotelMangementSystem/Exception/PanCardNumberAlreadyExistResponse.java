package com.patel.hotelMangementSystem.Exception;

public class PanCardNumberAlreadyExistResponse {
	private String panCardNumber;

	public PanCardNumberAlreadyExistResponse(String panCardNumber) {
		super();
		this.panCardNumber = panCardNumber;
	}

	public String getPanCardNumber() {
		return panCardNumber;
	}

	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}

}
