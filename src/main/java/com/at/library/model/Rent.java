package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rent implements Serializable {
	
	private static final long serialVersionUID = 4731219962512783574L;

	@Id
	@GeneratedValue
	private Integer id;

	@OneToOne
	private Book book;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Employee employee;

	@Temporal(TemporalType.DATE)
	private Date initDate;

	// The day the User should return the Book
	@Temporal(TemporalType.DATE)
	private Date returnDate;

	// The day the User returns the Book
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	private Boolean alreadyPunished = false;
		
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Boolean getAlreadyPunished() {
		return alreadyPunished;
	}

	public void setAlreadyPunished(Boolean alreadyPunished) {
		this.alreadyPunished = alreadyPunished;
	}
}
