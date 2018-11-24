package com.app.beans;

import com.app.service.UserService; 
import java.io.Serializable; 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlForm; 
import com.app.entities.User; 
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage; 
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher; import javax.servlet.ServletException; import javax.servlet.ServletRequest;
import java.io.Serializable;

import javax.servlet.ServletResponse;

import org.primefaces.context.RequestContext;
@ManagedBean(name = "registerBean")
@SessionScoped
public class RegisterBean implements Serializable{

	private String username;
	private String password;
	private String ConfirmPassword;
	
	@ManagedProperty(value = "#{userService}")
	UserService userService;
	
	public User userCtl = new User();
	HtmlForm formulaire;
	HtmlCommandButton maj;
	
	public String getUsername() {
		return username; 
		} 
	public void setUsername(String username) {
		this.username = username;
		} 
	public String getPassword() { 
		return password; 
		} 
	public void setPassword(String password) { 
		this.password = password;
	} 
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public UserService getUserService() {
		return userService;
	}

	public User getUserCtl() {
		return userCtl;
	}

	public void setUserCtl(User userCtl) { this.userCtl = userCtl; }

	public String getConfirmPassword() {
		return ConfirmPassword;
	}

	public void setConfirmPassword(String ConfirmPassword) {
		this.ConfirmPassword = ConfirmPassword;
	} // method CRUD public void createUser() {
		// userService.createUser(this.userCtl); }
	// LOGIN 
	public void login() throws ServletException, IOException { 
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
		username = context.getRequestParameterMap().get("username"); 
		//System.out.println("Login controller username2 is :-" + username);
	    RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/login");
	    dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse()); 
	    FacesContext.getCurrentInstance().responseComplete();
	    } 
	public String logout() throws IOException, ServletException {
		System.out.println("Login controller password is :-" + password);
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/logout"); 
		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse()); 
		FacesContext.getCurrentInstance().responseComplete();
		return null; 
		}
    public List<User> listUsers() {
    	return this.userService.listUsers();
    	}
}
