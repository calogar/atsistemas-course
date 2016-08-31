package com.at.library.dto;

import java.io.Serializable;

public class RentPostDTO extends DTO implements Serializable {

	// We use this DTO to create a Rent
	// Here we only consider the data we want the user to input
	// The end and initial date are calculated by ourselves
	
	private static final long serialVersionUID = 5000349539272833840L;

	private Integer book; // Not a whole Book or BookDTO, only the id
	
	private Integer user;
	
	private Integer employee;

	public Integer getBook() {
		return book;
	}

	public void setBook(Integer book) {
		this.book = book;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getEmployee() {
		return employee;
	}

	public void setEmployee(Integer employee) {
		this.employee = employee;
	}

	
}
