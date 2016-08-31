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
	 * Gets a book by id
	 * 
	 * @param id
	 * @return
	 */
	public BookDTO findById(Integer id);
	
	/**
	 * Creates a book
	 * 
	 * @param BookDTO with input data
	 * @return Created BookDTO
	 */
	public BookDTO create(BookDTO book); 
	
	/**
	 * Updates a book
	 * 
	 * @param BookDTO with input data
	 * @return Updated BookDTO
	 */
	public void update(BookDTO bookDTO);
	
	/**
	 * Deletes a book
	 * 
	 * @param Id of the Book we wanto to delete
	 * @return Deleted BookDTO
	 */
	public void delete(Integer id);
	
	/**
	 * Transforms a Book into a BookDTO
	 * 
	 * @param Book
	 * @return BookDTO
	 */
	BookDTO transform(Book book);

	/**
	 * Transforms a BookDTO into a Book
	 * 
	 * @param BookDTO
	 * @return Book
	 */
	Book transform(BookDTO book);

}
