package com.bluet.bring.getnewsrest.auth.exceptions;

public class CustomGlobalNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6590318243053957648L;

	public CustomGlobalNotFoundException(Long id) {
		super("Book id not found : " + id);
	}

}
