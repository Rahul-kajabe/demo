package com.employee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "First name is mandatory")
	@Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
	private String firstName;

	@NotBlank(message = "Last name is mandatory")
	@Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
	private String lastName;

	@NotBlank(message = "City is mandatory")
	@Size(min = 1, max = 100, message = "City must be between 1 and 100 characters")
	private String city;

	@NotBlank(message = "Mobile number is mandatory")
	@Pattern(regexp = "^\\d{10}$", message = "Mobile number must be a 10-digit number")
	private String mobileNumber;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}
