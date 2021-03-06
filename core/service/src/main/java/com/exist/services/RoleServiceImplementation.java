package com.exist.services;

import com.exist.model.Role;
import com.exist.dao.Dao;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
public class RoleServiceImplementation implements RoleService {

	@Autowired
	private Dao dao;

	@Transactional
	public void addRole(Role role) {
		dao.create(role);
	}

	@Transactional
	public void updateRole(Role role) {
		dao.update(role);
	}

	@Transactional
	public Role getRoleById(long id) {
		return (Role) dao.getById(id, "com.exist.model.Role");
	}

	@Transactional(readOnly = true)
	public List<Role> listRoles() {
		return (List<Role>) dao.getList("com.exist.model.Role");
	}
}