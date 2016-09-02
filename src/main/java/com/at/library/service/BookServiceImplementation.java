package com.at.library.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
import com.at.library.enums.BookStatus;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.IdNotMatchingException;
import com.at.library.model.Book;

@Service
public class BookServiceImplementation implements BookService {

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private DozerBeanMapper dozer;

	@Override	
	public List<BookDTO> search(String title, String isbn, String author, Pageable pageable) {
		final List<Book> books = bookDao.search(title, isbn, author, pageable);
		List<BookDTO> bookDTOs = new ArrayList();
		for(Book book : books) {
			bookDTOs.add(transform(book));
		}
		return bookDTOs;
	}

	@Override
	public BookDTO findOne(Integer id) throws BookNotFoundException {
		final Book book = bookDao.findOne(id);
		if(book == null)
			throw new BookNotFoundException();
		return transform(book);
	}
	
	@Override
	public BookDTO create(BookDTO bookDTO) {
		Book book = transform(bookDTO);
		book.setStartDate(new Date());
		book.setStatus(BookStatus.OK);
		return transform(bookDao.save(book));
	}
	
	@Override
	public void update(Integer id, BookDTO bookDTO) throws IdNotMatchingException {
		if (id != bookDTO.getId())
			throw new IdNotMatchingException();
		Book book = transform(bookDTO);
		// Set status to OK again to avoid override when assigning from DTO
		book.setStatus(BookStatus.OK);
		bookDao.save(book);
	}
	
	@Override
	public void delete(Integer id) throws BookNotFoundException {
		Book book = bookDao.findOne(id);
		if (book == null)
			throw new BookNotFoundException();
		// We set status to deleted instead of deleting it
		book.setStatus(BookStatus.DELETED);
		bookDao.save(book);
	}
	
	@Override
	public BookDTO transform(Book book) {
		return dozer.map(book, BookDTO.class);
	}

	@Override
	public Book transform(BookDTO book) {
		return dozer.map(book, Book.class);
	}

	@Override
	public boolean isAvailable(Integer id) throws BookNotFoundException {
		Book book = bookDao.findOne(id);		
		if(book == null)
			throw new BookNotFoundException();
		if(book.getStatus() != null && book.getStatus().equals(BookStatus.OK))
			return true;
		return false;
	}

}
