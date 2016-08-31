package com.at.library.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
import com.at.library.enums.BookStatusEnum;
import com.at.library.model.Book;

@Service
public class BookServiceImplementation implements BookService {

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private DozerBeanMapper dozer;
	
	// Get all the books and transforms them to DTOs
	@Override
	public List<BookDTO> findAll() {
		final Iterable<Book> findAll = bookDao.findAll();
		final Iterator<Book> iterator = findAll.iterator();
		final List<BookDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Book b = iterator.next();
			final BookDTO bDTO = transform(b);
			res.add(bDTO);
		}
		return res;
	}

	@Override
	public BookDTO findById(Integer id) {
		final Book b = bookDao.findOne(id);
		return transform(b);
	}
	
	@Override
	public BookDTO create(BookDTO bookDTO) {
		final Book book = transform(bookDTO);
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
