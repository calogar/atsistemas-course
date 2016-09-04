package com.at.library.dto;

import java.io.Serializable;
import java.util.Date;

public class HistoryRentDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 7103253036653136415L;
	
	private Date init;
	
	private Date end;
	
	private String title;
	
	private String isbn;
	
	private Integer idBook;
	
	public HistoryRentDTO(Date init, Date end, String title, String isbn, Integer idBook) {
		super();
		this.init = init;
		this.end = end;
		this.title = title;
		this.isbn = isbn;
		this.idBook = idBook;
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

	public Integer getIdBook() {
		return idBook;
	}

	public void setIdBook(Integer idBook) {
		this.idBook = idBook;
	}

	@Override
	public String toString() {
		return "HistoryRentedDTO [init=" + init + ", end=" + end + ", title=" + title + ", isbn=" + isbn + ", idBook="
				+ idBook + "]";
	}
}
