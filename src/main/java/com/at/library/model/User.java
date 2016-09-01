package com.at.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;

import com.at.library.enums.UserStatus;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = -3501156936037769057L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	//@Column(unique = true)
	private String dni;
	
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;

	@OneToMany
	private List<Rent> rents = new ArrayList<>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Employee employee;
	
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

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	} 
	
	public List<Rent> getRents() {
		return rents;
	}

	public void setRent(List<Rent> rents) {
		this.rents = rents;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
