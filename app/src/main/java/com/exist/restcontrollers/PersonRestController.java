package com.exist.restcontrollers;

import com.exist.model.Person;
import com.exist.model.ContactInformation;
import com.exist.services.PersonService;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/persons")
public class PersonRestController {
	
	@Autowired
	private PersonService personService;

	@Autowired
    private PasswordEncoder passwordEncoder;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<Person>> listPersons() {
		List<Person> persons = personService.listPersons();
		if (persons == null || persons.isEmpty()) {
			return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Person> getPerson(@PathVariable("id") long id) {
		Person person = personService.getPersonById(id);
		if (person == null) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<Void> addPerson(@RequestBody Person person, UriComponentsBuilder ucBuilder) {
		personService.addPerson(person);

		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/person-api/{id}").buildAndExpand(person.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
		Person newPerson = personService.getPersonById(id);
		if (newPerson == null) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		newPerson.setUsername(person.getUsername());
		newPerson.setPassword(person.getPassword());
		newPerson.setName(person.getName());
		newPerson.setContactInformation(person.getContactInformation());
		newPerson.setAddress(person.getAddress());
		newPerson.setGwa(person.getGwa());
		newPerson.setBirthday(person.getBirthday());
		newPerson.setDateHired(person.getDateHired());
		newPerson.setGender(person.getGender());
		newPerson.setCurrentlyEmployed(person.getCurrentlyEmployed());
		newPerson.setRoles(person.getRoles());
		personService.updatePerson(newPerson);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Person> deletePerson(@PathVariable("id") long id) {
		Person person = personService.getPersonById(id);
		if (person == null) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		personService.deletePerson(id);
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	}

}