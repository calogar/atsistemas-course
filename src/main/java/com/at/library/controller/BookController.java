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
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.service.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookservice;
	
	// Always thing in the depth of loggin when using it (debug, warnings...) 
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	@RequestMapping(method = { RequestMethod.GET })
	public List<BookDTO> getAll() {
		log.debug("Searching all books");
		return bookservice.findAll();
	}
	
	// Create must always return the created object so we can work with it
	// The controller ALWAYS works with the DTO, so we have to put the ids on them
	@RequestMapping(method = { RequestMethod.POST })
	public BookDTO create(@RequestBody BookDTO book) { // RequestBody gets the JSON and converts it to BookDTO
		log.debug(String.format("Creating this book: %s", book.toString()));
		return bookservice.create(book);
	}

	// PUT don't usually return the object (it may be the case)
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT})
	public void update(@PathVariable("id") Integer id, @RequestBody BookDTO book) {
		log.debug(String.format("Updating this book: %s", book));
		bookservice.update(book);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public BookDTO findOne(@PathVariable("id") Integer id) {
		log.debug(String.format("Getting book with id: %s", id));
		return bookservice.findById(id);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE})
	public void delete(@PathVariable("id") Integer id) {
		log.debug(String.format("Deleting book with id: %s", id));
		bookservice.delete(id);
	}
}
