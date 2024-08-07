package com.employee.service;

import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.employee.exception.CsvProcessingException;
import com.employee.model.Person;
import com.employee.repository.PersonRepo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class PersonService {

	@Autowired
	private PersonRepo personRepository;

	@Autowired
	private Validator validator;

	public List<Person> uploadCSV(@Valid MultipartFile file) throws IOException, CsvValidationException {
		List<Person> personList = new ArrayList<>();
		try {
			InputStreamReader fileInput = new InputStreamReader(file.getInputStream());
			CSVReader csvReader = new CSVReader(fileInput);
			String[] values;

			// Skip the header line
			csvReader.readNext();

			while ((values = csvReader.readNext()) != null) {
				if (values.length == 4) {
					Person person = new Person();
					person.setCity(values[0]);
					person.setFirstName(values[1]);
					person.setLastName(values[2]);
					person.setMobileNumber(values[3]);
					Set<ConstraintViolation<Person>> violations = validator.validate(person);
					if (!violations.isEmpty()) {
						StringBuilder errorMessage = new StringBuilder();
						for (ConstraintViolation<Person> violation : violations) {
							errorMessage.append(violation.getMessage()).append(" ");
						}
						throw new CsvProcessingException("Validation errors: " + errorMessage.toString().trim());
					}

					personList.add(person);
				} else {
					throw new CsvProcessingException("Invalid CSV format");
				}
			}
		} catch (IOException | CsvValidationException e) {
			throw new CsvProcessingException("Error processing CSV file: " + e.getMessage());
		}

		return personRepository.saveAll(personList);
	}
}
