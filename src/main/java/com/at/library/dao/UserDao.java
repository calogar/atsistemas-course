package com.at.library.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
	
	@Query(value = "select u from User as u where u.userStatus <> 'DELETED'" + 
				   "and (u.name  like %?1% or ?1 is null) " +
				   "and (u.dni   like %?2% or ?2 is null) ")
	public List<User> search(String name, String dni, Pageable pageable);
	
	@Query(value = "select u from User as u where u.id = ?1 and u.userStatus <> 'DELETED'")
	public User findOne(Integer id);

	@Query(value = "select u from User as u where u.userStatus = 'BANNED'")
	public List<User> findPunished();
}
