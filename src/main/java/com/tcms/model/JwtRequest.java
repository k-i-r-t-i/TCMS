package com.tcms.model;

public class JwtRequest {
	String username;
	String Password;
	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		Password = password;
		
	}
	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {
		return "JwtRequest [username=" + username + ", Password=" + Password + "]";
	}
	

}
