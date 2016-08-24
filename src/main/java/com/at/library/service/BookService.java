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
	 * Gets a book by its id
	 * 
	 * @param id
	 * @return
	 */
	public BookDTO findById(Integer id);
	
	/**
	 * Creates a book
	 * 
	 * @param
	 * @return
	 */
	public BookDTO create(BookDTO book); 
	
	/**
	 * Updates a book
	 * 
	 * @param
	 * @return
	 */
	public void update(BookDTO bookDTO);
	
	/**
	 * Deletes a book
	 * 
	 * @param BookDTO
	 * @return
	 */
	public void delete(Integer id);
	
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
