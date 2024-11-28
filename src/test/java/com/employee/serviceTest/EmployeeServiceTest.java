package com.employee.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.employee.exception.DuplicateResourceException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepo;
import com.employee.service.EmployeeService;

public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService empService;

	@Mock
	private EmployeeRepo empRepo;
	
	 private Employee emp;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		emp = new Employee();
		emp.setId(1L);
		emp.setFirstname("Rahul");
		emp.setLastName("Kajabe");
		emp.setEmailId("rahul@gmail.com");
	}

	@Test
	public void testSaveEmployee_Sucess() {
		when(empRepo.existsByEmailId(emp.getEmailId())).thenReturn(false);// if block we need to break for success test
		when(empRepo.save(emp)).thenReturn(emp);

		Employee savedEmp = empService.saveEmployee(emp);
		assertNotNull(savedEmp);
		assertEquals("rahul@gmail.com", savedEmp.getEmailId());
		
		verify(empRepo, times(1)).existsByEmailId(emp.getEmailId());
		verify(empRepo, times(1)).save(emp);


	}
	
	@Test
	public void testSaveEmployee_duplicate() {
		when(empRepo.existsByEmailId(emp.getEmailId())).thenReturn(true);
		assertThrows(DuplicateResourceException.class, ()->empService.saveEmployee(emp));

		
	}
   
	@Test
	public void testGetAllListOfEmployee() {
		List<Employee> empList=new ArrayList<>();
		when(empRepo.findAll()).thenReturn(empList);
		List<Employee> listEmp=empService.getAllListOfEmployee();
		assertNotNull(listEmp);
		assertEquals(1, listEmp.size());
	}
	
	@Test
	public void testGetEmployeeByID_success() {
		
		when(empRepo.findById(emp.getId())).thenReturn(Optional.of(emp));
	    Employee employee=	empService.getEmployeeById(emp.getId());
	    assertNotNull(employee);
	    assertEquals(1, employee.getId());
	}
	@Test
	public void testGetEmployeeByID_NotFound() {
		
		when(empRepo.findById(emp.getId())).thenReturn(Optional.empty());
	    assertThrows(ResourceNotFoundException.class, ()->empService.getEmployeeById(emp.getId()));
	}
}
