package com.at.library.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Employee;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer> {

	@Query(value = "select e from Employee as e")
	public List<Employee> findAll(Pageable pageable);
}
