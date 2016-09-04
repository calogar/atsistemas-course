package com.at.library.dto;

import java.io.Serializable;
import java.util.Date;

public class RentDTO extends DTO implements Serializable {

	private static final long serialVersionUID = -5994667441227258415L;

	private Integer id;
	
	private Date initDate;
	
	private Date returnDate;

	private Date endDate;
	
	private BookDTO book;

	private UserDTO user;

	private EmployeeDTO employee;

	public RentDTO() {
		super();
	}
	
	public RentDTO(Integer id, Date initDate, Date returnDate, Date endDate, BookDTO book, UserDTO user, EmployeeDTO employee) {
		super();
		this.id = id;
		this.initDate = initDate;
		this.returnDate = returnDate;
		this.endDate = endDate;
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

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
		return "RentDTO [id=" + id + ", initDate=" + initDate + ", returnDate=" + returnDate + ", endDate=" + endDate
				+ ", book=" + book + ", user=" + user + ", employee=" + employee + "]";
	}
	
	
}