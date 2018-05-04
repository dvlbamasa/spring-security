package com.exist.restcontrollers;

import com.exist.model.Person;
import com.exist.model.ContactInformation;
import com.exist.services.PersonService;

import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/contacts")
public class ContactRestController {
	
	@Autowired
	private PersonService personService;

	@RequestMapping(value="/")
	public ResponseEntity<List<ContactInformation>> listContacts() {
		List<Person> persons = personService.listPersons();
		List<ContactInformation> contacts = new ArrayList<ContactInformation>();
		if (persons == null || persons.isEmpty()) {
			return new ResponseEntity<List<ContactInformation>>(HttpStatus.NO_CONTENT);
		}
		for (Person person : persons) {
			contacts.add(person.getContactInformation());
		}
		return new ResponseEntity<List<ContactInformation>>(contacts, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<ContactInformation> getContact(@PathVariable("id") long id) {
		Person person = personService.getPersonById(id);
		if (person == null || person.getContactInformation() == null) {
			return new ResponseEntity<ContactInformation>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ContactInformation>(person.getContactInformation(), HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<ContactInformation> updatePerson(@PathVariable("id") long id, @RequestBody ContactInformation contactInformation) {
		Person newPerson = personService.getPersonById(id);
		if (newPerson == null || newPerson.getContactInformation() != null) {
			return new ResponseEntity<ContactInformation>(HttpStatus.NOT_FOUND);
		}
		newPerson.setContactInformation(contactInformation);
		personService.updatePerson(newPerson);
		return new ResponseEntity<ContactInformation>(newPerson.getContactInformation(), HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<ContactInformation> deleteContact(@PathVariable("id") long id) {
		Person person = personService.getPersonById(id);
		if (person == null || person.getContactInformation() == null) {
			return new ResponseEntity<ContactInformation>(HttpStatus.NOT_FOUND);
		}
		person.setContactInformation(null);
		personService.updatePerson(person);		
		personService.deleteContact(id);
		return new ResponseEntity<ContactInformation>(HttpStatus.NO_CONTENT);
	}

}