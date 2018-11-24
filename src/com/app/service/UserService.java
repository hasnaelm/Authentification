package com.app.service;

import java.util.List;

import com.app.entities.User;

public interface UserService {
	public void createUser(User user);
	public List<User> listUsers();
}
