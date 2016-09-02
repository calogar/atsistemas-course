package com.at.library.exceptions;

import java.io.Serializable;

public class EmployeeNotFoundException extends Exception implements Serializable {
	
	private static final long serialVersionUID = 1026343075849625315L;
	
	private static final String message = "The employee you have requested hast' been found";
	
	@Override
	public String getMessage() {
		return message;
	}

}
