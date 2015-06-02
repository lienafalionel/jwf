package org.esgi.web.framework.module.user;


public class User {

	public int id;
	public String firstName;
	public String lastName;
	public String login;
	public String password;
	public String role;

	public User() {
		
	}
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public User(String login, String password, String firstName, String lastName, String role) {
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
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

}
