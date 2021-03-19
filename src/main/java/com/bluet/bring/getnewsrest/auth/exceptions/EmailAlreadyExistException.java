package com.bluet.bring.getnewsrest.auth.exceptions;

import org.springframework.security.core.AuthenticationException;

public class EmailAlreadyExistException extends AuthenticationException {

		private static final long serialVersionUID = 1L;

		/**
		 * Constructs a <code>UsernameNotFoundException</code> with the specified message.
		 * @param msg the detail message.
		 */
		public EmailAlreadyExistException(String msg) {
			super(msg);
		}

		/**
		 * Constructs a {@code UsernameNotFoundException} with the specified message and root
		 * cause.
		 * @param msg the detail message.
		 * @param cause root cause
		 */
		public EmailAlreadyExistException(String msg, Throwable cause) {
			super(msg, cause);
		}


}
