package com.exist.restcontrollers;

import com.exist.model.Role;
import com.exist.services.RoleService;

import java.util.List;
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
@RequestMapping("/roles")
public class RoleRestController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value="/")
	public ResponseEntity<List<Role>> listRoles() {
		List<Role> roles = roleService.listRoles();
		if (roles.isEmpty()) {
			return new ResponseEntity<List<Role>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Role> getRole(@PathVariable("id") long id) {
		Role role = roleService.getRoleById(id);
		if (role == null) {
			return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<Void> addRole(@RequestBody Role role, UriComponentsBuilder ucBuilder) {
		roleService.addRole(role);

		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/role-api/{id}").buildAndExpand(role.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Role> updateRole(@PathVariable("id") long id, @RequestBody Role role) {
		Role newRole = roleService.getRoleById(id);
		if (newRole == null) {
			return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
		}
		newRole.setName(role.getName());
		newRole.setPersons(role.getPersons());
		roleService.updateRole(newRole);
		return new ResponseEntity<Role>(HttpStatus.OK);
	}
}