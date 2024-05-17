package com.app.library_system.response_model;

public class LoginTokenResponse {
public String token;
public String token_type;
public String message;
public LoginTokenResponse() {
	// TODO Auto-generated constructor stub
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
public String getToken_type() {
	return token_type;
}
public void setToken_type(String token_type) {
	this.token_type = token_type;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public LoginTokenResponse(String token, String token_type,String message) {
	this.token = token;
	this.token_type = token_type;
	this.message = message;
}


}
