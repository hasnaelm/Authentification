package com.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.app.entities.Role;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Role findByRoleId(int userId) {
		return (Role) sessionFactory.getCurrentSession()
				.get(Role.class, userId);
	}

	@Override
	public void createRole(Role role) {
		Role createdRole = role;
		role.toString();
		sessionFactory.getCurrentSession().save(createdRole);
	}

}
