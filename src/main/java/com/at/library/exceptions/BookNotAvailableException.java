package com.at.library.exceptions;

import java.io.Serializable;

public class BookNotAvailableException extends Exception implements Serializable {

	private static final long serialVersionUID = -6517504463336574164L;

	private static String message = "The Book you have requested is not available";
	
	@Override
	public String getMessage() {
		return message;
	}
}
