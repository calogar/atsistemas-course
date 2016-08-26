package com.at.library.dto;

import java.io.Serializable;

public class RentPostDTO extends DTO implements Serializable {

	// We use this DTO to create a Rent
	// Here we only consider the data we want the user to input
	// The end and initial date are calculated by ourselves
	
	private static final long serialVersionUID = 5000349539272833840L;

	private Integer idBook; // Not a whole Book or BookDTO, only the id
	
	private Integer idUser;
	
	private Integer idEmployee;

	@Override
	public String toString() {
		return "RentPostDTO [idBook=" + idBook + ", idUser=" + idUser + ", idEmployee=" + idEmployee + "]";
	}

	public Integer getIdBook() {
		return idBook;
	}

	public void setIdBook(Integer idBook) {
		this.idBook = idBook;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}
	
}
