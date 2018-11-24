package com.app.dao;

import com.app.entities.User;
import com.app.entities.Role;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.primefaces.context.RequestContext;
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	private User user = new User();
	 @Autowired
	 private SessionFactory sessionFactory;
	 @Autowired//@Inject or #(ManagedProperty) can also be used
	 private RoleDao roleDao;
	 // Setters & Getters
	 public User getUser() {
	 return user;
	 }
	 public void setUser(User user) {
	 this.user = user;
	 }
	 public RoleDao getRoleDao() {
		 return roleDao;
		 }
		 public void setRoleDao(RoleDao roleDao) {
		 this.roleDao = roleDao;
		 }
		 // Overreide methods
		 @Override
		 public int CreateNewUserId() {
		 Session session = this.sessionFactory.getCurrentSession();
		 String hql = "SELECT max(u.userId) FROM User u";
		 Query query = session.createQuery(hql);
		 List<Integer> results = query.list();
		 int newid = 1;
		 if (results.get(0) != null) {
		 newid = results.get(0) + 1;
		 }
		 return newid;
		 }
		 @Override
		 public void createUser(User user) {
		 try {
		 int newid = this.CreateNewUserId();
		 String cryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		 //Setting User
		 user.setUserId(newid);
		 user.setFirstname(user.getFirstname());
		 user.setLastname(user.getLastname());
		 user.setUsername(user.getUsername());
		 user.setEmail(user.getEmail());
		 user.setPassword(cryptedPassword);
		 user.setEnabled(user.getEnabled());
		 // settin Role
		 Role role = new Role();
		 role = roleDao.findByRoleId(newid);
		 Role newrole = new Role();
		 if (role == null) {
		 newrole.setRoleId(newid);
		 newrole.setUser(user);
		 newrole.setRole("ROLE_USER");
		 }
		 Set<Role> Roles = new HashSet<Role>(0);
		 Roles.add(role);
		 user.setRoles(Roles);
		 //valid password & set form
		 sessionFactory.getCurrentSession().save(user);
		 getRoleDao().createRole(newrole);
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save ",
		"User Information saved successfully.");
		 RequestContext.getCurrentInstance().showMessageInDialog(message);
		 // return "login";
		 } catch (DataAccessException e) {
		 e.printStackTrace();
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save ",
		"Failed to save User Information .");
		 RequestContext.getCurrentInstance().showMessageInDialog(message);
		//return null;
		 }
		 }
		 @Override
		 public User findByUsername(String username) {
		 List<User> users = new ArrayList<User>();
		 users = sessionFactory.getCurrentSession()
				 .createQuery("from User where username=?")
				 .setParameter(0, username)
				 .list();
		 if (users.size() > 0) {
			 return users.get(0);
			 } else {
			 return null;
			 }
			 }
		 @SuppressWarnings("unchecked")
		 @Override
		 public List<User> listUsers() {
		 Session session = this.sessionFactory.getCurrentSession();
		 List<User> userList = session.createQuery("SELECT u.userId,u.firstname,u.lastname,u.username,u.email,u.password,u.enabled,r.role from User u,Role r WHERE u.userId = r.user").list();
		 return userList;
		 }
}