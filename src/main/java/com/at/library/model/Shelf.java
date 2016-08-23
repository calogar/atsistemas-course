package com.at.library.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Shelf implements Serializable {

	private static final long serialVersionUID = 6374272004167410735L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer number;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Book> books;
	
	@ManyToOne
	private Room room;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}