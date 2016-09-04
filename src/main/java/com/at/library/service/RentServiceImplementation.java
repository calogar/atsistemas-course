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
import com.at.library.dto.HistoryRentDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.dto.UserDTO;
import com.at.library.enums.BookStatus;
import com.at.library.exceptions.BookNotAvailableException;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.exceptions.IdNotMatchingException;
import com.at.library.exceptions.PunishedUserException;
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
																		  EmployeeNotFoundException,
																		  PunishedUserException {
		if(idBook != rentPostDTO.getBook()) // getBook returns an id
			throw new IdNotMatchingException();
		
		if(!bookService.isAvailable(idBook))
			throw new BookNotAvailableException();
		
		final User user = userService.findOne(rentPostDTO.getUser()); //getUser returns an id
		
		if(userService.isPunished(user))
			throw new PunishedUserException();
		
		Book book = bookService.transform(bookService.findOne(idBook));
		final EmployeeDTO employeeDTO = employeeService.findOne(rentPostDTO.getEmployee()); //getEmployee returns an id
		final Employee employee = employeeService.transform(employeeDTO);
		
		bookService.changeStatus(book, BookStatus.RENTED);
		
		Rent rent = new Rent();
		rent.setInitDate(new Date());
		rent.setReturnDate(calculateReturnDate(new Date()));
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
		
		rent.setEndDate(new Date());
		bookService.changeStatus(book, BookStatus.OK);
		return transform(rentDao.save(rent));
	}

	@Override
	public List<HistoryRentDTO> getBookHistory(Integer idBook, Pageable pageable) {
		List<Rent> rents = rentDao.findBookHistory(idBook, pageable);
		List<HistoryRentDTO> rentHistoryDTOs = new ArrayList();
		for(Rent rent : rents) {
			rentHistoryDTOs.add(transformHistoryDTO(rent));
		}
		return rentHistoryDTOs;
	}

	@Override
	public List<HistoryRentDTO> getUserHistory(Integer idUser, Pageable pageable) {
		List<Rent> rents = rentDao.findUserHistory(idUser, pageable);
		List<HistoryRentDTO> rentHistoryDTOs = new ArrayList();
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
	public HistoryRentDTO transformHistoryDTO(Rent rent) {
		return new HistoryRentDTO(rent.getInitDate(),
								  rent.getReturnDate(),
								  rent.getBook().getTitle(),
								  rent.getBook().getIsbn(),
								  rent.getBook().getId());
	}

	@Override
	public Date calculateReturnDate(Date startDate) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, 3); // Return books after three days by default
        return calendar.getTime();
	}

	@Override
	public List<Rent> findPunishable() {
		return rentDao.findPunishable();
	}

	@Override
	public void setAlreadyPunished(Rent rent) {
		rent.setAlreadyPunished(true);
		rentDao.save(rent);
	}

	@Override
	public RentDTO findOne(Integer id) throws RentNotFoundException {
		Rent rent = rentDao.findOne(id);
		if(rent == null)
			throw new RentNotFoundException();
		return transform(rent);
	}
	
}
