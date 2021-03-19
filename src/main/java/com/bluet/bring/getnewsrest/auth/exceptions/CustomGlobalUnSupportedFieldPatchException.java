package com.bluet.bring.getnewsrest.auth.exceptions;

import java.util.Set;

public class CustomGlobalUnSupportedFieldPatchException extends RuntimeException {


	private static final long serialVersionUID = -2248095251643441811L;

	public CustomGlobalUnSupportedFieldPatchException(Set<String> keys) {
		super("Field " + keys.toString() + " update is not allow.");
	}

}