package com.at.library.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.at.library.dao.RentDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.EmployeeDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.dto.UserDTO;
import com.at.library.enums.BookStatus;
import com.at.library.exceptions.BookNotAvailableException;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.exceptions.IdNotMatchingException;
import com.at.library.exceptions.RentNotFoundException;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.Book;
import com.at.library.model.Employee;
import com.at.library.model.Rent;
import com.at.library.model.User;

@Service
public class RentServiceImplementation implements RentService {

	@Autowired
	private RentDao rentDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Autowired
	private BookService bookService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<RentDTO> getAll(Pageable pageable) {
		List<Rent> rents = rentDao.findAll(pageable);
		List<RentDTO> rentDTOs = new ArrayList();
		for (Rent rent : rents) {
			rentDTOs.add(transform(rent));
		}
		return rentDTOs;
	}

	@Override
	public RentDTO create(Integer idBook, RentPostDTO rentPostDTO) throws IdNotMatchingException,
																		  BookNotFoundException,
																		  BookNotAvailableException,
																		  UserNotFoundException,
																		  EmployeeNotFoundException {
		if(idBook != rentPostDTO.getBook()) // getBook returns an id
			throw new IdNotMatchingException();
		
		if(!bookService.isAvailable(idBook))
			throw new BookNotAvailableException();
		
		// TODO: Check user is not banned
		
		Book book = bookService.transform(bookService.findOne(idBook));
		final EmployeeDTO employeeDTO = employeeService.findOne(rentPostDTO.getEmployee()); //getEmployee returns an id
		final Employee employee = employeeService.transform(employeeDTO);
		final UserDTO userDTO = userService.findOne(rentPostDTO.getUser()); //getUser returns an id
		final User user = userService.transform(userDTO);
		
		bookService.changeStatus(book, BookStatus.RENTED);
		
		Rent rent = new Rent();
		rent.setCreatedAt(new Date());
		rent.setEndAt(calculateEndDate(new Date()));
		rent.setEmployee(employee);
		rent.setBook(book);
		rent.setUser(user);
		
		return transform(rentDao.save(rent));
	}

	@Override
	public RentDTO delete(Integer idBook) throws BookNotFoundException, RentNotFoundException {
		final BookDTO bookDTO = bookService.findOne(idBook);
		Book book = bookService.transform(bookDTO);
		Rent rent = rentDao.findNotReturnedRentByBookId(idBook);
		
		if(rent == null)
			throw new RentNotFoundException();
		
		rent.setReturnAt(new Date());
		bookService.changeStatus(book, BookStatus.OK);
		return transform(rentDao.save(rent));
	}

	@Override
	public List<HistoryRentedDTO> getBookHistory(Integer idBook, Pageable pageable) {
		List<Rent> rents = rentDao.findBookHistory(idBook, pageable);
		List<HistoryRentedDTO> rentHistoryDTOs = new ArrayList();
		for(Rent rent : rents) {
			rentHistoryDTOs.add(transformHistoryDTO(rent));
		}
		return rentHistoryDTOs;
	}

	@Override
	public List<HistoryRentedDTO> getUserHistory(Integer idUser, Pageable pageable) {
		List<Rent> rents = rentDao.findUserHistory(idUser, pageable);
		List<HistoryRentedDTO> rentHistoryDTOs = new ArrayList();
		for(Rent rent : rents) {
			rentHistoryDTOs.add(transformHistoryDTO(rent));
		}
		return rentHistoryDTOs;
	}

	@Override
	public RentDTO transform(Rent rent) {
		return dozer.map(rent, RentDTO.class);
	}

	@Override
	public Rent transform(RentDTO rentDTO) {
		return dozer.map(rentDTO, Rent.class);
	}

	@Override
	public HistoryRentedDTO transformHistoryDTO(Rent rent) {
		return new HistoryRentedDTO(rent.getCreatedAt(),
								  rent.getEndAt(),
								  rent.getBook().getTitle(),
								  rent.getBook().getIsbn(),
								  rent.getBook().getId());
	}

	@Override
	public Date calculateEndDate(Date startDate) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, 3); // Return books after three days by default
        return calendar.getTime();
	}
}
