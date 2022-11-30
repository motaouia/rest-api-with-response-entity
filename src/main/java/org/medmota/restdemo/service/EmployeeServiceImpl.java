package org.medmota.restdemo.service;

import java.util.List;
import java.util.Optional;

import org.medmota.restdemo.entity.Employee;
import org.medmota.restdemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Integer id) {
		return employeeRepository.findById(id);
	}

	@Override
	public void deleteEmployeeById(Integer id) {
		try {
			Optional<Employee> employeeData = getEmployeeById(id);
			if (employeeData.isPresent()) {
				employeeRepository.delete(employeeData.get());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

}
