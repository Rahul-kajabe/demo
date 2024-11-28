package com.employee.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.employee.model.Person;

public class PersonRepoCri {
	
	private final EntityManager entityManger;
	
	public PersonRepoCri(EntityManager entityManager) {
		this.entityManger=entityManager;
		
	}
	
	public List<Person> findPersons(String firstName, String lastName, String city, String mobileNumber) {
		//create the criteria Builder
	CriteriaBuilder cb=	entityManger.getCriteriaBuilder();
	
	
	//create query
	CriteriaQuery cq= cb.createQuery(Person.class);
	
	//root of query
	Root<Person> person=cq.from(Person.class);
	//create the list to hold the query condition
	List<Predicate> predicates=new ArrayList<>();
	
	
    if (firstName != null && !firstName.isEmpty()) {
    	predicates.add((Predicate) cb.equal(person.get("firstName"), firstName));
    }

    if (lastName != null && !lastName.isEmpty()) {
    	predicates.add((Predicate) cb.equal(person.get("lastName"), lastName));
    }

    if (city != null && !city.isEmpty()) {
        predicates.add((Predicate) cb.equal(person.get("city"), city));
    }

    if (mobileNumber != null && !mobileNumber.isEmpty()) {
        predicates.add((Predicate) cb.equal(person.get("mobileNumber"), mobileNumber));
    }
    
    // Apply the predicates to the CriteriaQuery
     cq.where((javax.persistence.criteria.Predicate[]) predicates.toArray(new Predicate[0]));

    // Execute the query and return the results
    return entityManger.createQuery(cq).getResultList();
	}

}
