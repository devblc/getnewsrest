package com.bluet.bring.getnewsrest.auth.dto;

import javax.validation.constraints.Email;

public class EmailConfirmationDto {

	@Email
	private String email;
	
	public EmailConfirmationDto() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
