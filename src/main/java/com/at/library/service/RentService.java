package com.at.library.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

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
	  																  EmployeeNotFoundException,
	  																  PunishedUserException;
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
	public List<HistoryRentDTO> getBookHistory(Integer idBook, Pageable pageable);
	
	/**
	 * Returns all the Rents for a given User
	 * @param The id of the User
	 * @param A Pageable object
	 * @return A list of Rents
	 */
	public List<HistoryRentDTO> getUserHistory(Integer idUser, Pageable pageable);
	
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
	public HistoryRentDTO transformHistoryDTO(Rent rent);
	
	/**
	 * Calculates the date when the user must return the book
	 * @param startDate
	 * @return endDate
	 */
	public Date calculateReturnDate(Date startDate);

	/**
	 * Finds all the Rents that have expired and must be punished
	 * 
	 * @return List of Rents
	 */
	public List<Rent> findPunishable();

	/**
	 * Sets a punished Rent as already punished, so the user won't be punished again
	 * 
	 * @param rent
	 */
	public void setAlreadyPunished(Rent rent);

	/**
	 * Finds a Rent by id
	 *
	 * @param Id of the Rent
	 * @return RentDTO
	 */
	public RentDTO findOne(Integer id) throws RentNotFoundException;
	
	
}
