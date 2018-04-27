package com.exist.services;

import com.exist.model.Person;
import com.exist.dao.Dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("personService")
public class PersonServiceImplementation implements PersonService{

	@Autowired
	private Dao dao;

	@Autowired
    private PasswordEncoder passwordEncoder;

	@Transactional
	public void addPerson(Person person) {
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		dao.create(person);
	}

	@Transactional
	public void updatePerson(Person person) {
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		dao.update(person);
	}

	@Transactional
	public List<Person> listPersons() {
		return dao.getList("com.exist.model.Person");
	}

	@Transactional
	public Person getPersonById(long id) {
		return (Person) dao.getById(id, "com.exist.model.Person");
	}

	@Transactional
	public Person getPersonByUserName(String userName) {
		return (Person) dao.getByUserName(userName, "com.exist.model.Person");
	}

	@Transactional
	public void deletePerson(long id) {
		dao.delete(id, "com.exist.model.Person");
	}

	@Transactional(readOnly = true)
	public List<Person> listPersonsOrderBy(String orderType) {
		return dao.getOrderedList("com.exist.model.Person", orderType);
	}

	@Transactional
	public void deleteContact(long id) {
		dao.delete(id, "com.exist.model.ContactInformation");
	}

}