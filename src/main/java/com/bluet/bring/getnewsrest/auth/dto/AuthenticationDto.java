package com.bluet.bring.getnewsrest.auth.dto;

import java.io.Serializable;

public class AuthenticationDto implements Serializable {

	private static final long serialVersionUID = -8630066845784490960L;

	private String userName;
	private String password;

	public AuthenticationDto() {

	}

	public AuthenticationDto(String userName, String password) {
		this.setUserName(userName);
		this.setPassword(password);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
