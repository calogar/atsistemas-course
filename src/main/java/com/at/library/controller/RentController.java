package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.HistoryRentDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.exceptions.BookNotAvailableException;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.exceptions.IdNotMatchingException;
import com.at.library.exceptions.PunishedUserException;
import com.at.library.exceptions.RentNotFoundException;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.service.RentService;

@RestController
public class RentController {

	@Autowired
	private RentService rentService;
	
	private static final Logger log = LoggerFactory.getLogger(RentController.class);
	
	@RequestMapping(value = "rent", method = { RequestMethod.GET })
	public List<RentDTO> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
		    					@RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
		log.debug(String.format("Getting all rents"));
		return rentService.getAll(new PageRequest(page, size));
	}
	
	@RequestMapping(value = { "book/{id}/rent", "rent/{id}" }, method = { RequestMethod.POST })
	public RentDTO create(@PathVariable("id") Integer idBook, @RequestBody RentPostDTO rentPostDTO)
																throws IdNotMatchingException,
	  															BookNotFoundException,
	  															BookNotAvailableException,
	  															UserNotFoundException,
	  															EmployeeNotFoundException,
	  															PunishedUserException { 
		log.debug(String.format("Renting this book: %s", idBook));
		return rentService.create(idBook, rentPostDTO);
	}
	
	@RequestMapping(value = { "book/{id}/rent", "rent/{id}" }, method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer idBook) throws BookNotFoundException, RentNotFoundException {
		log.debug(String.format("Returning this book: %s", idBook));
		rentService.delete(idBook);
		
	}

	@RequestMapping(value = "book/{id}/rent", method = { RequestMethod.GET })
	public List<HistoryRentDTO> getBookHistory(@PathVariable("id") Integer idBook,
									    @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
									    @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) throws BookNotFoundException {
		log.debug(String.format("History of the book: %s", idBook));
		return rentService.getBookHistory(idBook, new PageRequest(page, size));
	}

	@RequestMapping(value = "user/id/rent", method = { RequestMethod.GET })
	public List<HistoryRentDTO> getUserHistory(@PathVariable Integer idUser,
										@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
										@RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
		log.debug(String.format("History of the user: %s", idUser));
		return rentService.getUserHistory(idUser, new PageRequest(page, size));
	}

	@RequestMapping(value = "rent/{id}", method = { RequestMethod.GET })
	public RentDTO findOne(@PathVariable("id") Integer id) throws RentNotFoundException {
		log.debug(String.format("Getting the rent: %s", id));
		return rentService.findOne(id);
	}
}
