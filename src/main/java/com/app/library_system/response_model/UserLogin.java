package com.app.library_system.response_model;

public class UserLogin {
	public String email_id;
	public String password;

	public UserLogin() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserLogin(String email_id, String password) {
		this.email_id = email_id;
		this.password = password;
	}
	

}
