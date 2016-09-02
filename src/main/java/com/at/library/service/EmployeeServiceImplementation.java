package com.at.library.service;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.at.library.dao.EmployeeDao;
import com.at.library.dto.EmployeeDTO;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.model.Employee;

public class EmployeeServiceImplementation implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DozerBeanMapper dozer;
	
	@Override
	public EmployeeDTO findOne(Integer id) throws EmployeeNotFoundException {
		final Employee employee = employeeDao.findOne(id);
		if(employee == null)
			throw new EmployeeNotFoundException();
		return transform(employee);
	}

	@Override
	public EmployeeDTO transform(Employee employee) {
		return dozer.map(employee, EmployeeDTO.class);
	}

	@Override
	public Employee transform(EmployeeDTO employeeDTO) {
		return dozer.map(employeeDTO, Employee.class);
	}

}
