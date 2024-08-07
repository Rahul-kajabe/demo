package com.employee.controller;

import java.io.IOException;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.employee.model.Person;
import com.employee.service.PersonService;
import com.opencsv.exceptions.CsvValidationException;

@RestController
@RequestMapping("/person")
@Validated
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping("/upload")
	public ResponseEntity<List<Person>> uploadCSV(@Valid @RequestParam("file") MultipartFile file)
			throws CsvValidationException, IOException {

		List<Person> savedPersons = personService.uploadCSV(file);
		return ResponseEntity.ok(savedPersons);

	}

}
