package com.at.library.exceptions;

import java.io.Serializable;

public class PunishedUserException extends Exception implements Serializable {

	private static final long serialVersionUID = -5169836427968790714L;
	
	private static final String message = "The user is banned and cannot rent any book.";
	
	@Override
	public String getMessage() {
		return message;
	}
}
