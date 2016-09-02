package com.at.library.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
import com.at.library.enums.BookStatusEnum;
import com.at.library.model.Book;
import com.at.library.model.User;

@Service
public class BookServiceImplementation implements BookService {

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private DozerBeanMapper dozer;

	@Override	
	public List<BookDTO> search(String title, String isbn, String author) {
		final List<Book> books = bookDao.search(title, isbn, author);
		List<BookDTO> bookDTOs = new ArrayList();
		for(Book book : books) {
			bookDTOs.add(transform(book));
		}
		return bookDTOs;
	}

	@Override
	public BookDTO findById(Integer id) {
		final Book book = bookDao.findOne(id);
		if(book == null)
			// TODO: throw an exception
		return transform(book);
	}
	
	@Override
	public BookDTO create(BookDTO bookDTO) {
		Book book = transform(bookDTO);
		book.setStartDate(new Date());
		book.setStatus(BookStatusEnum.AVAILABLE);
		// Using transform because we must return a BookDTO
		return transform(bookDao.save(book));
	}
	
	@Override
	public void update(Integer id, BookDTO bookDTO) {
		// TODO: Check that the id corresponds to the bookDTO and throw exception if not
		final Book book = transform(bookDTO);
		bookDao.save(book);
	}
	
	@Override
	public void delete(Integer id) {
		// TODO: Check that the book exists and throw exception if not
		Book book = bookDao.findOne(id);
		// We set status to unavailable instead of deleting it
		book.setStatus(BookStatusEnum.UNAVAILABLE);
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

}
