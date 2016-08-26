package com.at.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Shelf implements Serializable {

	private static final long serialVersionUID = 7540076976125262757L;

	@Id
	private String code;
	
	private Integer number;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Book> books = new ArrayList<>();
	
	// We can get this info by doing a query
	// @ManyToOne
	// private Room room;
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	/*
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	*/

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
