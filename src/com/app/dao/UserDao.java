package com.app.dao;
import com.app.entities.User;
import java.util.List;

public interface UserDao {

	 public void createUser(User user);
	 public int CreateNewUserId();
	 public User findByUsername(String username);
	 public List<User> listUsers();
}
