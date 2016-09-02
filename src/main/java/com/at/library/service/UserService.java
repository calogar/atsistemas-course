package com.at.library.service;

import java.util.List;

import com.at.library.dto.UserDTO;

public interface UserService {
	
	/**
	 * Creates a User and sets it's status to ACTIVE
	 * @param UserDTO with input params
	 * @return Created UserDTO
	 */
	public UserDTO create(UserDTO userDTO);

	/**
	 * Sets the User status to SUSPENDED
	 * @param The id of the User
	 * @return 
	 */
	public void delete(Integer id);
	
	/**
	 * Searchs Users by name, by dni or both
	 * @param Name of the User
	 * @param Dni of the User
	 */
	public List<UserDTO> search(String name, String dni);
	
	/**
	 * Get an User by it's id
	 * @param Id of the user
	 * @return The UserDTO
	 */
	public UserDTO findOne(Integer id);
}
