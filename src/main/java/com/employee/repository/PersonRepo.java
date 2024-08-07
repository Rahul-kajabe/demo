package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.employee.model.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer>{

}
