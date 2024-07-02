package com.tcms.model;

import com.tcms.payloads.UserDto;

import lombok.Data;

public class JwtResponse {
	private String token;
	private UserDto user;
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JwtResponse(String token, UserDto user) {
		super();
		this.token = token;
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
    
	

}
