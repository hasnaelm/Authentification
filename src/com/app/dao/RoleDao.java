package com.app.dao;

import com.app.entities.Role;

public interface RoleDao {
	public void createRole(Role userRole);
	// public int CreateNewRoleId(); 
	public Role findByRoleId(int userId);
}
