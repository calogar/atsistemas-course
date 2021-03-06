package com.at.library.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Rent;

@Repository
public interface RentDao extends CrudRepository<Rent, Integer> {

	@Query(value = "select r from Rent as r")
	public List<Rent> findAll(Pageable pageable);

	@Query(value = "select r from Rent as r where r.book.id = ?1")
	public List<Rent> findBookHistory(Integer idBook, Pageable pageable);
	
	@Query(value = "select r from Rent as r where r.user.id = ?1")
	public List<Rent> findUserHistory(Integer idUser, Pageable pageable);

	@Query(value = "select r from Rent as r where r.book.id = ?1 and r.endDate is null")
	public Rent findNotReturnedRentByBookId(Integer idBook);

	@Query(value = "select r from Rent as r where r.endDate is null and r.returnDate < CURRENT_DATE and r.alreadyPunished = false")
	public List<Rent> findPunishable();
	
	@Query(value = "select r from Rent as r where r.id = ?1")
	public Rent findOne(Integer id);
}
