package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.dto.BookDTO;
import com.at.library.model.Book;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {

	// Lo normal aquí es coger el modelo y devolver una lista de Libros, no de LibrosDTO. Pero si queremos evitar coger más datos de los que
	// necesitamos (si la tabla tuviera 400 columns), podemos hacer una excepción y buscar un LibroDTO.
	@Query(value = "select new com.at.library.dto.BookDTO(b.id, b.isbn, b.title, b.author) from Book as b where b.id in (select r.id from Rent as r where r.endAt is not null)")
	public List<BookDTO> findUnavailable();

	@Query(value = "select b from Book as b where status <> 'RENTED' " +
				   "and (b.title  like %?1% or ?1 is null) " +
				   "and (b.isbn   like %?2% or ?2 is null) " + 
				   "and (b.author like %?3% or ?3 is null)")
	public List<Book> search(String title, String isbn, String author);

	@Query(value = "select b from Book as b where b.id = ?1 and status <> 'RENTED' ")
	public Book findOne(Integer id);
}
