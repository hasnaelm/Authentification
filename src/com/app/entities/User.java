package com.app.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="user"
 ,catalog="userappcon"
)
public class User implements java.io.Serializable {
	 
	 private int userId;
	 private String firstname;
	 private String lastname;
	 private String email;
	 private String username;
	 private String password;
	 private Boolean enabled;
	 private Set<Role> roles = new HashSet<Role>(0);
	
	 @Id
	 @Column(name="userId", unique=true, nullable=false)
	 public int getUserId() {
		return userId;
	  }
	 public void setUserId(int userId) {
		this.userId = userId;
	   }
	 @Column(name="firstname", nullable=false, length=50)
	 public String getFirstname() {
		return firstname;
	  }
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	@Column(name="lastname", nullable=false, length=50)
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Column(name="email", nullable=false, length=50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="username", nullable=false, length=50)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password", nullable=false, length=50)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="enabled", nullable=false) 
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="user")
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public void reset(){
		 this.userId = 0;
		 this.firstname ="";
		 this.lastname ="";
		 this.email ="";
		 this.password = "";
		 this.username ="";
		 this.roles = null;
		 this.enabled= true;

		 }
		 //This method writes the values of contact object with System.out.println(user.toString())code
		 @Override
		 public String toString() {
		 return "User is :-"
		 + "\n\t Firstname:- " + this.firstname 
		 + "\n\t Lastname:- " + this.lastname
		 + "\n\t UserName:- " + this.username
		 + "\n\t Email:- " + this.email
		 + "\n\t Password:- " + this.password
		 +"\n\t Authority:- " + this.getRoles();
		 }

	 
}
