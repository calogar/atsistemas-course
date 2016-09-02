package com.at.library.dto;

public class ErrorDTO extends DTO {

	private Integer error;
	
	private String message;
	
	public ErrorDTO() {
		
	}
	
	public ErrorDTO(Integer error, String message) {
		this.setError(error);
		this.setMessage(message);
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorDTO [error=" + error + ", message=" + message + "]";
	}
}
