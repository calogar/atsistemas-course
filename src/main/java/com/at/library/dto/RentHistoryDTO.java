package com.at.library.dto;

import java.io.Serializable;
import java.util.Date;

public class RentHistoryDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 7103253036653136415L;
	
	private Date init;
	
	private Date end;
	
	private String title;
	
	private String isbn;
	
	private Integer book;
	
	public RentHistoryDTO(Date init, Date end, String title, String isbn, Integer book) {
		super();
		this.init = init;
		this.end = end;
		this.title = title;
		this.isbn = isbn;
		this.book = book;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getInit() {
		return init;
	}

	public void setInit(Date init) {
		this.init = init;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getBook() {
		return book;
	}

	public void setBook(Integer book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "RentHistoryDTO [init=" + init + ", end=" + end + ", title=" + title + ", isbn=" + isbn + ", book="
				+ book + "]";
	}
}
