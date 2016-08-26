package com.at.library.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Rent implements Serializable {
	
	private static final long serialVersionUID = 4731219962512783574L;

	@EmbeddedId
	private RentPK rentPK;
	
	@Temporal(TemporalType.DATE)
	private Date endAt;
	
	// Date we must return the book
	@Temporal(TemporalType.DATE)
	private Date returnAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Employee employee;

	@Transient
	public Date getCreatedAt() {
		return rentPK.getCreatedAt();
	}
	
	@Transient
	public void setCreatedAt(Date createdAt) {
		rentPK.setCreatedAt(createdAt);
	}
	
	@Transient
	public Book getBook() {
		return rentPK.getBook();
	}

	@Transient
	public void setBook(Book book) {
		rentPK.setBook(book);
	}
	
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
}
