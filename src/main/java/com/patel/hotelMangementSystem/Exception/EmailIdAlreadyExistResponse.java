package com.patel.hotelMangementSystem.Exception;

public class EmailIdAlreadyExistResponse {
	private String email;

	public EmailIdAlreadyExistResponse(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
