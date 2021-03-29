package com.bluet.bring.getnewsrest.blogbuilder;

import com.bluet.bring.getnewsrest.enviroment.EnviromentMessages;

public class ProfileNotFoundException extends Exception {

		private static final long serialVersionUID = 1L;
		private String message;
		private String lang;

		/**
		 * Constructs a <code>ProfileNotFoundException</code> with the specified message.
		 * @param msg the detail message.
		 */
		public ProfileNotFoundException(String lang) {
			this.lang = lang;
		}

		/**
		 * Constructs a <code>ProfileNotFoundException</code> with the specified message.
		 * @param msg the detail message.
		 */
		public ProfileNotFoundException(String message, String lang) {
			super(message);
			this.lang = lang;
			this.message = message;
		}

		/**
		 * Constructs a {@code ProfileNotFoundException} with the specified message and root
		 * cause.
		 * @param cause root cause
		 */
		public ProfileNotFoundException(Throwable cause) {
			super(cause);
		}
		
		
	    @Override
	    public String toString() {
	        return new EnviromentMessages(lang).ProfileNotFound();
	    }

	    @Override
	    public String getMessage() {
	        return message;
	    }



}
