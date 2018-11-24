package com.app.service;

import com.app.dao.RoleDao;
import com.app.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleDao roleDao;

	public void createRole(Role userRole) {
		roleDao.createRole(userRole);
	}
}
