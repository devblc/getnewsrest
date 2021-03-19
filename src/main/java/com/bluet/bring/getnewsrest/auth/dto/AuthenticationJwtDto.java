package com.bluet.bring.getnewsrest.auth.dto;

import java.io.Serializable;

public class AuthenticationJwtDto implements Serializable {

	private static final long serialVersionUID = -4262843474982659536L;
	private final String jwt;

	public AuthenticationJwtDto(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
