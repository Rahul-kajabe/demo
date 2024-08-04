package com.employee.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.exception.DuplicateResourceException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo empRepo;

	@Transactional
	public Employee saveEmployee(Employee emp) {
        if (empRepo.existsByEmailId(emp.getEmailId())) {
            throw new DuplicateResourceException("Employee with this email already exists");
        }
		return empRepo.save(emp);
	}

	public List<Employee> getAllListOfEmployee() {
		return empRepo.findAll();
	}

	public Employee getEmployeeById(long id) {

		return empRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    
	}

	public Employee updateEmployee(Employee emp) {
		Optional<Employee> existingEmpOpt = empRepo.findById(emp.getId());

		return existingEmpOpt.map(existingEmp -> {
			boolean isUpdated = false;
			if (emp.getFirstname() != null && !emp.getFirstname().equals(existingEmp.getFirstname())) {
				existingEmp.setFirstname(emp.getFirstname());
				isUpdated = true;
			}

			if (emp.getLastName() != null && !emp.getLastName().equals(existingEmp.getLastName())) {
				existingEmp.setLastName(emp.getLastName());
				isUpdated = true;
			}

			if (emp.getEmailId() != null && !emp.getEmailId().equals(existingEmp.getEmailId())) {
				existingEmp.setEmailId(emp.getEmailId());
				isUpdated = true;
			}

			// Save and return the updated employee only if there were changes
			if (isUpdated) {
				return empRepo.save(existingEmp);
			} else {
				return existingEmp; // No changes, return the existing record
			}
		}).orElse(null);
	}
}
