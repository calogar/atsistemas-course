package com.at.library.dto;

import java.io.Serializable;

public class UserDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 2262372118503019667L;

	private Integer id;

	private String name;
	
	private String dni;
	
	public UserDTO() {
		
	}
	
	public UserDTO(String name, String dni) {
		this.setName(name);
		this.setDni(dni);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", dni=" + dni + "]";
	}
}
