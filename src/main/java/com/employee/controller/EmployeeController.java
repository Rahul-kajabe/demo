package com.employee.controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;


@RestController
@RequestMapping("/api/v1")
@Validated
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/allEmpList")
	public List<Employee> getListOfEmployee(){
		return empService.getAllListOfEmployee();
	}

	
	@PostMapping("/saveEmp")
	public Employee saveEmployee(@Valid @RequestBody Employee emp) {
		
		return empService.saveEmployee(emp);
	}
	
	@GetMapping("/getEmpById/{id}")
	public Employee getEmployeeById(@PathVariable long id) {
		return empService.getEmployeeById(id);
	}
	
	@PutMapping("/updateEmp")
	public Employee updateEmployee(@RequestBody Employee emp) {
		return empService.updateEmployee(emp);
	}
}
