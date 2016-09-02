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

import com.at.library.dto.UserDTO;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@RequestMapping(method = { RequestMethod.POST })
	public UserDTO create(@RequestBody UserDTO userDTO) {
		log.debug(String.format("Creating this user: %s", userDTO.toString()));
		return userService.create(userDTO);
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE } )
	public void delete(@PathVariable("id") Integer id) throws UserNotFoundException {
		log.debug(String.format("Deleting this user: %s", id));
		userService.delete(id);
	}
	
	@RequestMapping(method = { RequestMethod.GET })
	public List<UserDTO> search(@RequestParam(value = "name", required = false) String name,
								@RequestParam(value = "dni", required = false) String dni,
								@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
								@RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
		log.debug(String.format("Searching users by: %s, %s", dni, name));		
		return userService.search(name, dni, new PageRequest(page, size));
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public UserDTO findOne(@PathVariable("id") Integer id) throws UserNotFoundException {
		log.debug(String.format("Getting this user: %s", id));
		return userService.findOne(id);
	}
}
