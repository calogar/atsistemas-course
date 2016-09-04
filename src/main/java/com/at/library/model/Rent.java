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
	private Date createdAt;

	// The day the rent ends
	@Temporal(TemporalType.DATE)
	private Date endAt;

	// The day the User returns the Book
	@Temporal(TemporalType.DATE)
	private Date returnAt;
		
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
