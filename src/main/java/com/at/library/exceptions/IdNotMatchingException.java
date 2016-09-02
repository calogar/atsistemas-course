package com.at.library.exceptions;

import java.io.Serializable;

public class IdNotMatchingException extends Exception implements Serializable {

	private static final long serialVersionUID = -414226465458785647L;

	private static final String message = "The id of the path and the id of the given object doesn't match.";

	@Override
	public String getMessage() {
		return message;
	}
}
