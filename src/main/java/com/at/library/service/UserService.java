package com.at.library.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.library.dto.UserDTO;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.User;

public interface UserService {

	/**
	 * Creates a User and sets it's status to ACTIVE
	 * 
	 * @param UserDTO
	 *            with input params
	 * @return Created UserDTO
	 */
	public UserDTO create(UserDTO userDTO);

	/**
	 * Sets the User status to SUSPENDED
	 * 
	 * @param The
	 *            id of the User
	 * @return
	 */
	public void delete(Integer id) throws UserNotFoundException;

	/**
	 * Searchs Users by name, by dni or both
	 * 
	 * @param Name
	 *            of the User
	 * @param Dni
	 *            of the User
	 */
	public List<UserDTO> search(String name, String dni, Pageable pageable);

	/**
	 * Get an User by it's id
	 * 
	 * @param Id of the user
	 * @return User
	 */
	public User findOne(Integer id) throws UserNotFoundException;

	/**
	 * Transforms a User into a UserDTO
	 * 
	 * @param User
	 * @return UserDTO
	 */
	public UserDTO transform(User user);

	/**
	 * Transforms a UserDTO into a User
	 * 
	 * @param UserDTO
	 * @return User
	 */
	public User transform(UserDTO userDTO);

	/**
	 * Checks if a given user is currently punished
	 *
	 * @param
	 * @return Boolean
	 */
	public Boolean isPunished(User user);	
}
