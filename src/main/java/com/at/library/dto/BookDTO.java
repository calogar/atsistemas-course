package com.at.library.dto;

import java.io.Serializable;

import com.at.library.enums.BookStatus;

public class BookDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1583585532736761521L;

	private Integer id;
	
	private String isbn;

	private String title;

	private String author;

	private BookStatus status;
	
	// New fields
	private String description;
	
	private String image;
	
	private String year;
	
	public BookDTO(Integer id, String isbn, String title, String author, BookStatus status) {
		this.setId(id);
		this.setIsbn(isbn);
		this.setTitle(title);
		this.setAuthor(author);
		this.setStatus(status);
	}

	public BookDTO() {
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + ", description="
				+ description + ", image=" + image + ", year=" + year + "]";
	}

}
