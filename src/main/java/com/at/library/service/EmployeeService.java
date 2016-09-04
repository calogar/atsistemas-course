package com.at.library.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.library.dto.EmployeeDTO;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.model.Employee;

public interface EmployeeService {

	/**
	 * Creates a new Employee
	 * 
	 * @param EmployeeDTo with input data
	 * @return The created EmployeeDTO
	 */
	public EmployeeDTO create(EmployeeDTO employeeDTO);
	
	/**
	 * Finds and paginates Employees
	 * @param pageable
	 * @return List of EmployeeDTOs
	 */
	public List<EmployeeDTO> findAll(Pageable pageable);
	
	/**
	 * Finds an Employee by if
	 * @param Id of the employee
	 * @return EmployeeDTO
	 */
	public EmployeeDTO findOne(Integer id) throws EmployeeNotFoundException;
	
	/**
	 * Transforms an Employee into a EmployeeDTO
	 * 
	 * @param Employee
	 * @return EmployeeDTO
	 */
	EmployeeDTO transform(Employee employee);

	/**
	 * Transforms an EmployeeDTO into an Employee
	 * 
	 * @param EmployeeDTO
	 * @return Employee
	 */
	Employee transform(EmployeeDTO employeeDTO);
}
