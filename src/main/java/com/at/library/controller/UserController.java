package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.UserDTO;

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
	public void delete(@PathVariable("id") Integer id) {
		log.debug(String.format("Deleting this user: %s", id));
		userService.delete(id);
	}
	
	@RequestMapping(method = { RequestMethod.GET })
	public List<UserDTO> search(@RequestParam(value = "name", required = false) String name,
								@RequestParam(value = "dni", required = false) String dni) {
		return userService.search(name, dni);
	}
}
