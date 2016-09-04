package com.at.library.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.at.library.dao.EmployeeDao;
import com.at.library.dto.EmployeeDTO;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.model.Employee;

@Service
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

	@Override
	public EmployeeDTO create(EmployeeDTO employeeDTO) {
		Employee employee = transform(employeeDTO);
		return transform(employeeDao.save(employee));
	}

	@Override
	public List<EmployeeDTO> findAll(Pageable pageable) {
		List<Employee> employees = employeeDao.findAll(pageable);
		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
		for(Employee employee : employees) {
			employeeDTOs.add(transform(employee));
		}
		return employeeDTOs;
	}

}
