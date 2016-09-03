package com.at.library.dto;

import java.io.Serializable;
import java.util.Date;

public class RentDTO extends DTO implements Serializable {

	private static final long serialVersionUID = -5994667441227258415L;

	private Integer id;
	
	private Date createdAt;
	
	private Date endAt;

	private Date returnAt;
	
	private BookDTO book;

	private UserDTO user;

	private EmployeeDTO employee;

	public RentDTO() {
		super();
	}
	
	public RentDTO(Integer id, Date createdAt, Date endAt, Date returnAt, BookDTO book, UserDTO user, EmployeeDTO employee) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.endAt = endAt;
		this.returnAt = returnAt;
		this.book = book;
		this.user = user;
		this.employee = employee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public Date getReturnAt() {
		return returnAt;
	}

	public void setReturnAt(Date returnAt) {
		this.returnAt = returnAt;
	}

	public BookDTO getBook() {
		return book;
	}

	public void setBook(BookDTO book) {
		this.book = book;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "RentDTO [id=" + id + ", createdAt=" + createdAt + ", endAt=" + endAt + ", returnAt=" + returnAt
				+ ", book=" + book + ", user=" + user + ", employee=" + employee + "]";
	}
	
	
}