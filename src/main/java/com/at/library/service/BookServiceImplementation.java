package com.at.library.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
import com.at.library.model.Book;

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
	public BookDTO transform(Book book) {
		return dozer.map(book, BookDTO.class);
	}

	@Override
	public Book transform(BookDTO book) {
		return dozer.map(book, Book.class);
	}

}