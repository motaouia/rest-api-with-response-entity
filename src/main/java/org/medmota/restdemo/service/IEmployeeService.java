package org.medmota.restdemo.service;

import java.util.List;
import java.util.Optional;

import org.medmota.restdemo.entity.Employee;

public interface IEmployeeService {
	
	Employee saveEmployee(Employee empl);
	List<Employee> getAllEmployees();
	Optional<Employee> getEmployeeById(Integer id);
	void deleteEmployeeById(Integer id);

}
