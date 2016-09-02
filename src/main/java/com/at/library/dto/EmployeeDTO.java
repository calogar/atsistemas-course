package com.at.library.dto;
import java.io.Serializable;

public class EmployeeDTO extends DTO implements Serializable {

	private static final long serialVersionUID = -7059139813298343819L;

	private Integer id;

	private String name;
	
	private String dni;

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
		return "EmployeeDTO [id=" + id + ", name=" + name + ", dni=" + dni + "]";
	}
}
