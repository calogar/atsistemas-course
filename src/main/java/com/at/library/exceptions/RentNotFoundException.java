package com.at.library.exceptions;

import java.io.Serializable;

public class RentNotFoundException extends Exception implements Serializable {
	
	private static final long serialVersionUID = -4396315036937805682L;
	
	private static final String message = "The requested rent hasn't been found";
	
	public String getMessage() {
		return message;
	}

}
