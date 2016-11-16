package org.hni.security.om;

import org.hni.user.om.User;

public class AuthenticationResult {

	private int status;
	private User user;
	private String token;
	private String message;
	
	public AuthenticationResult() {}
	public AuthenticationResult(int status, String message) {
		this.status = status;
		this.message = message;
	}
	public AuthenticationResult(int status, User user, String token, String message) {
		this.status = status;
		this.message = message;
		this.user = user;
		this.token = token;
		this.message = message;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
