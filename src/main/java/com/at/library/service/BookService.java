package com.at.library.service;

import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.model.Book;

public interface BookService {

	/**
	 * Searches all the books in the system
	 * 
	 * @return list of books
	 */
	List<BookDTO> findAll();

	/**
	 * Transforms a Book into a BookDTO
	 * 
	 * @param book
	 * @return
	 */
	BookDTO transform(Book book);

	/**
	 * Transforms a BookDTO into a Book
	 * 
	 * @param book
	 * @return
	 */
	Book transform(BookDTO book);

}
