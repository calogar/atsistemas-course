package com.at.library.exceptions;

import java.io.Serializable;

public class UserNotFoundException extends Exception implements Serializable {

	private static final long serialVersionUID = -414226465458785647L;

	private static final String message = "The requested user hasn't been found.";

	@Override
	public String getMessage() {
		return message;
	}
}
