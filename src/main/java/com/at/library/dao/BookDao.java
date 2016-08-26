package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.dto.BookDTO;
import com.at.library.model.Book;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {

	
	// This is safe while we don't use external params in the query
	// Using JPQL
	@Query(value = "select new BookDTO(b.id, b.isbn, b.title, b.author) from Book as b where b.id in (select r.rentPK.book.id from Rent as r where r.endDate is not null)")
	public List<BookDTO> findUnavailable();
}
