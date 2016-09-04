package com.at.library.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.GABookDTO;
import com.at.library.dto.GAVolumeInfoDTO;
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

	@Autowired
	private RestTemplate restTemplate;

	private static final String GoogleApisBookURL = "https://www.googleapis.com/books/v1/volumes";
	
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
		// Setting attributes from GoogleApis
		setGoogleApisAttributes(book);
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

	@Override
	public void changeStatus(Book book, BookStatus bookStatus) {
		book.setStatus(bookStatus);
		bookDao.save(book);
	}

	/**
	 * Inserts the query String in the URL and returns it
	 * @param query
	 * @return GoogleApis Url
	 */
	private String getGoogleApisQuery(String query) {
		return GoogleApisBookURL + "?startIndex=0&maxResults=1" +
								   "&fields=items(volumeInfo/description,volumeInfo/publishedDate,volumeInfo/imageLinks/thumbnail)" +
								   "&q=" + query;
	}
	
	/**
	 * Queries GoogleApis to retrieve information and save it in our Book model
	 * @param book
	 */
	private void setGoogleApisAttributes(Book book) {
		
		final String query = getGoogleApisQuery(book.getTitle());
		final GABookDTO apiData = restTemplate.getForObject(query, GABookDTO.class);

		if (apiData != null) {
			final GAVolumeInfoDTO bookData = apiData.getItems()[0].getVolumeInfo();
			
			final Integer year = Integer.parseInt(bookData.getPublishedDate().substring(0, 4)); 
			book.setYear(year);
			book.setDescription(bookData.getDescription());
			book.setImage(bookData.getImageLinks().get("thumbnail"));
		}
	}

}
