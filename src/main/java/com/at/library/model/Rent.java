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

	//@EmbeddedId
	//private RentPK rentPK;

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

	@Temporal(TemporalType.DATE)
	private Date endAt;
	
	@Temporal(TemporalType.DATE)
	private Date returnAt;
		
	/*
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
	*/
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
