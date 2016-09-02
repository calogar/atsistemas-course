package com.at.library.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.library.dto.RentDTO;
import com.at.library.dto.RentHistoryDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.exceptions.BookNotAvailableException;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.exceptions.IdNotMatchingException;
import com.at.library.exceptions.RentNotFoundException;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.Rent;

public interface RentService {
	
	/**
	 * Returns a list with all the rents
	 * @param Pageable object
	 * @return List of RentDTOs
	 */
	public List<RentDTO> getAll(Pageable pageable);
	
	/**
	 * Creates a Rent (rents a Book to a User)
	 * @param The id of the book
	 * @param A RentDTO with input data
	 * @return The created RentDTO
	 */
	public RentDTO create(Integer idBook, RentPostDTO rentDTO) throws IdNotMatchingException,
	  																  BookNotFoundException,
	  																  BookNotAvailableException,
	  																  UserNotFoundException,
	  																  EmployeeNotFoundException;
	/**
	 * Deletes a Rent (returns a Book)
	 * @param The id of the Book
	 */
	public RentDTO delete(Integer idBook) throws BookNotFoundException, RentNotFoundException;
	
	/**
	 * Returns all the Rents for a given Book
	 * @param The id of the Book
	 * @param A Pageable object
	 * @return A list of Rents
	 */
	public List<RentHistoryDTO> getBookHistory(Integer idBook, Pageable pageable);
	
	/**
	 * Returns all the Rents for a given User
	 * @param The id of the User
	 * @param A Pageable object
	 * @return A list of Rents
	 */
	public List<RentHistoryDTO> getUserHistory(Integer idUser, Pageable pageable);
	
	/**
	 * Transforms a Rent into a RentDTO
	 * 
	 * @param Rent
	 * @return RentDTO
	 */
	public RentDTO transform(Rent rent);

	/**
	 * Transforms a RentDTO into a Rent
	 * 
	 * @param RentDTO
	 * @return Rent
	 */
	public Rent transform(RentDTO rentDTO);

	/**
	 * Transforms a Rent to a DTO
	 * @param rent
	 * @return RentHistoryDTO
	 */
	public RentHistoryDTO transformHistoryDTO(Rent rent);
	
	/**
	 * Calculates the date when the user must return the book
	 * @param startDate
	 * @return endDate
	 */
	public Date calculateEndDate(Date startDate);
}
