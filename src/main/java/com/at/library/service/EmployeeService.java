package com.at.library.service;

import com.at.library.dto.EmployeeDTO;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.model.Employee;

public interface EmployeeService {

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
