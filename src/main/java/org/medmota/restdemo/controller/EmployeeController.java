package org.medmota.restdemo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.medmota.restdemo.entity.Employee;
import org.medmota.restdemo.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@GetMapping("/list")
	public List<Employee> empList() {
		return employeeService.getAllEmployees();

	}

	@PostMapping("/save")
	public Employee save(@RequestBody Employee emp) {
		return employeeService.saveEmployee(emp);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmpById(@PathVariable Integer id) {
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmp(@PathVariable Integer id,@RequestBody Employee emp){
		Employee updateEmp=employeeService.getEmployeeById(id).get();
		updateEmp.setFirstName(emp.getFirstName());
		updateEmp.setLastName(emp.getLastName());
		updateEmp.setEmail(emp.getEmail());
		return  ResponseEntity.ok(employeeService.saveEmployee(updateEmp));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmp(@PathVariable Integer id){
		Map<String,Boolean> map=new LinkedHashMap<String,Boolean>();
		employeeService.deleteEmployeeById(id);
		map.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(map);
	}

}
