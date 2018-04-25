package com.exist.controllers;

import com.exist.model.Role;
import com.exist.model.Person;
import com.exist.model.ContactInformation;
import com.exist.services.PersonService;
import com.exist.services.RoleService;
import com.exist.validations.FormValidation;
import com.exist.util.Util;

import java.util.List;
import java.util.HashSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@Controller
@RequestMapping(value="/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private RoleService roleService;

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView listPersons(ModelAndView modelAndView,
									@RequestParam(value="order_type", required=false) String orderType,
									@RequestParam(value="prompt", required=false) String prompt) {
		
		List<Person> persons = null;
		String subTitle = "";
		if (orderType != null) {
			if (orderType.equals("GWA")) {
			persons = personService.listPersons();
            Collections.sort(persons, (person1, person2) -> {
               return Float.compare(person1.getGwa(), person2.getGwa());
            });
			}
			else if (orderType.equals("Date Hired")) {
				persons = personService.listPersonsOrderBy("dateHired");
			}
			else if (orderType.equals("Last Name")) {
				persons = personService.listPersonsOrderBy("name.lastName");
			}
			subTitle = "List of Persons Ordered by " + orderType;
		}
		else {
			persons = personService.listPersons();	
			subTitle = "List of Persons";
		}
		if (persons == null || persons.isEmpty()) {
			modelAndView.setViewName("noPersons");
		}
		else {
			modelAndView.addObject("prompt", prompt);
			modelAndView.addObject("title", "Person");
			modelAndView.addObject("subTitle", subTitle);
			modelAndView.addObject("persons", persons);
			modelAndView.setViewName("listPersons");
		}
		return modelAndView;
	}

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addPerson(ModelAndView modelAndView) {
		Person person = new Person();
		List<Role> roles = roleService.listRoles();
		modelAndView.addObject("title", "Add Person");
		modelAndView.addObject("person", person);
		modelAndView.addObject("roles", roles);
		modelAndView.setViewName("personForm");
		return modelAndView;
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView savePerson(@ModelAttribute("person") Person person, BindingResult result) {
		FormValidation formValidation = new FormValidation();
		formValidation.validate(person, result);
		if (result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("personForm");
			List<Role> roles = roleService.listRoles();
			person.setBirthday(Util.parseDate(person.getBirthday()));
			person.setDateHired(Util.parseDate(person.getDateHired()));
			modelAndView.addObject("person", person);
			modelAndView.addObject("roles", roles);
			modelAndView.setViewName("personForm");
			if (person.getId() == 0) {
				modelAndView.addObject("title", "Add Person");
				return modelAndView;
			}
			else {
				modelAndView.addObject("title", "Update Person");
				return modelAndView;
			}
		}
		else {
			ModelAndView modelAndView = new ModelAndView("redirect:/person/list");
			addRoles(person, roleService.listRoles());
			if (person.getId() == 0) {
				personService.addPerson(person);
				modelAndView.addObject("prompt", "Successfully added a Person!");
			}
			else {
				personService.updatePerson(person);
				modelAndView.addObject("prompt", "Successfully updated a Person!");
			}
			return modelAndView;
		}
	}

	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ModelAndView deletePerson(@RequestParam(value="personId", required=true) long id) {
		personService.deletePerson(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/person/list");
		modelAndView.addObject("prompt", "Successfully deleted a Person!");
		return modelAndView;
	}

	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView updatePerson(@RequestParam(value="personId", required=true) long id) {
		Person person = personService.getPersonById(id);
		if (person.getContactInformation() == null) {
			person.setContactInformation(new ContactInformation());
		}
		ModelAndView modelAndView = new ModelAndView("personForm");
		List<Role> roles = roleService.listRoles();
		modelAndView.addObject("person", person);
		modelAndView.addObject("roles", roles);
		modelAndView.addObject("title", "Update Person");
		return modelAndView;
	}

	public void addRoles(Person person, List<Role> roles) {
		HashSet<Role> newRoles = new HashSet<Role>();
		for(Role role : roles) {
			Iterator<Role> iterator = person.getRoles().iterator();
			while(iterator.hasNext()) {
				Role setRole = iterator.next();
				if(setRole.getName().equals(role.getId()+"")) {
					newRoles.add(role);
				}
			}
		}
		person.getRoles().clear();
		person.setRoles(newRoles);	
	}
}