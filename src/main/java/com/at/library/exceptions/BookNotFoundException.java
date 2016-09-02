package com.at.library.exceptions;

import java.io.Serializable;

public class BookNotFoundException extends Exception implements Serializable {

	private static final long serialVersionUID = -414226465458785647L;

	private static final String message = "The requested book hasn't been found.";

	@Override
	public String getMessage() {
		return message;
	}
}
