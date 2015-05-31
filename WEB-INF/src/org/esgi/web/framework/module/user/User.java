package org.esgi.web.framework.module.user;

import java.util.ArrayList;
import java.util.List;

public class User {

	public String login;
	public String password;
	public List<String> roles;

	public User() {
		
	}
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
		this.roles = new ArrayList<String>();
		this.roles.add("admin");
		this.roles.add("user");
	}

	public User(String login, String password, List<String> roles) {
		this.login = login;
		this.password = password;
		this.roles = roles;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
