package com.bluet.bring.getnewsrest.blogbuilder;


public class ProfileAlreadyExistException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>ProfileAlreadyExistException</code> with the specified message.
	 * @param msg the detail message.
	 */
	public ProfileAlreadyExistException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a {@code ProfileNotFoundException} with the specified message and root
	 * cause.
	 * @param msg the detail message.
	 * @param cause root cause
	 */
	public ProfileAlreadyExistException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

