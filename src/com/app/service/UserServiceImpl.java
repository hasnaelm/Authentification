package com.app.service;

import com.app.dao.UserDao;
import com.app.entities.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl {
	@Autowired 
	UserDao userDao;
	@Transactional
	public void createUser(User user) { 
		userDao.createUser(user); 
	}
    @Transactional 
	public List<User> listUsers() {
		return this.userDao.listUsers();
		}
}
