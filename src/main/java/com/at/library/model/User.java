package com.at.library.model;

import java.io.Serializable;
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

	private static final long serialVersionUID = 6374272004167410735L;
	
	@Id
	@GeneratedValue
	private Integer Id;
	
	private String name;
	
	private String surname;
	
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;

	@OneToMany
	private List<Rent> rents;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Employee employee;
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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
