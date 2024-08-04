package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    //@Query("select email_id from employee where email_id= emailId")	
	boolean existsByEmailId(String emailId);

}
