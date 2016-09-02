package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.at.library.dto.DTO;
import com.at.library.enums.BookStatus;

@Entity
public class Book extends DTO implements Serializable {

	private static final long serialVersionUID = 3443610166569017522L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String isbn;
	
	private String title;
	
	private String author;

	@Enumerated(EnumType.STRING)
	private BookStatus status;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	//@OneToOne(fetch=FetchType.LAZY)
	private Rent rent;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Employee employee;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Shelf shelf;
	
	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + ", status=" + status
				+ ", startDate=" + startDate + ", rent=" + rent + ", employee=" + employee + ", shelf=" + shelf + "]";
	}
}
