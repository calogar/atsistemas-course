package com.at.library.controller;

import java.util.List;

// Using this because it's the configurated logger for spring
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.service.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookservice;
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
		
	@RequestMapping(method = { RequestMethod.GET })
	List<BookDTO> search(@RequestParam(value = "title", required = false) String title,
						 @RequestParam(value = "isbn", required = false) String isbn,
						 @RequestParam(value = "author", required = false) String author) {
		log.debug("Searching Books by:");
		if(title != null)
			log.debug(String.format(" title:", title));
		if(isbn != null)
			log.debug(String.format(" isbn:", isbn));
		if(author != null)
			log.debug(String.format(" author:", author));
		return bookservice.search(title, isbn, author);
	}
	
	// Create must always return the created object so we can work with it
	// The controller ALWAYS works with the DTO, so we have to put the ids on them
	@RequestMapping(method = { RequestMethod.POST })
	public BookDTO create(@RequestBody BookDTO bookDTO) { // RequestBody gets the JSON and converts it to BookDTO
		log.debug(String.format("Creating this book: %s", bookDTO.toString()));
		return bookservice.create(bookDTO);
	}

	// PUT don't usually return the object (it may be the case)
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(@PathVariable("id") Integer id, @RequestBody BookDTO bookDTO) {
		log.debug(String.format("Updating this book: %s", bookDTO));
		bookservice.update(id, bookDTO);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public BookDTO findOne(@PathVariable("id") Integer id) {
		log.debug(String.format("Getting book with id: %s", id));
		return bookservice.findById(id);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer id) {
		log.debug(String.format("Deleting book with id: %s", id));
		bookservice.delete(id);
	}
}
