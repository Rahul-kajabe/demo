package com.employee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.sun.istack.NotNull;

@Entity
@Table(name="employee")
public class Employee {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "First name is required")
	@Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
	private String firstname;
	
	@NotBlank(message = "Last name is required")
	@Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
	private String lastName;
	
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
	private String emailId;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Employee(long id, String firstname, String lastName, String emailId) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastName = lastName;
		this.emailId = emailId;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstname=" + firstname + ", lastName=" + lastName + ", emailId=" + emailId
				+ "]";
	}
	
	

}
