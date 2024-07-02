package com.tcms.payloads;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcms.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserDto {
	private Long userId;
	@NotEmpty
	private String salutation;
	@NotEmpty
	@Size(min=2,message="name must be minimum of 2 characters")
	private String first_name;
	@NotEmpty
	private String last_name;
	@NotEmpty
	private String tel_code;
	@NotEmpty
	private String mobile;
	@NotEmpty
	@Email(message="Email is not valid")
	private String email;
	@NotEmpty
	private String username;
	@NotEmpty
	@Size(min=3, max=10,message="password must be between 3-10 characters")
	private String password;
	@NotEmpty
	private String passwordHint;
	@NotEmpty
	private String security_ques_1;
	@NotEmpty
	private String security_ans_1;
	@NotEmpty
	private String security_ques_2;
	@NotEmpty
	private String security_ans_2;
	private Set<RoleDto> roles=new HashSet<>();
	
	public UserDto(Long userId, String salutation, String first_name, String last_name, String tel_code, String mobile,
			String email, String username, String password, String passwordHint, String security_ques_1,
			String security_ans_1, String security_ques_2, String security_ans_2, Set<RoleDto> roles
			) {
		super();
		this.userId = userId;
		this.salutation = salutation;
		this.first_name = first_name;
		this.last_name = last_name;
		this.tel_code = tel_code;
		this.mobile = mobile;
		this.email = email;
		this.username = username;
		this.password = password;
		this.passwordHint = passwordHint;
		this.security_ques_1 = security_ques_1;
		this.security_ans_1 = security_ans_1;
		this.security_ques_2 = security_ques_2;
		this.security_ans_2 = security_ans_2;
		this.roles = roles;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getTel_code() {
		return tel_code;
	}

	public void setTel_code(String tel_code) {
		this.tel_code = tel_code;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/*public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}*/

	public String getPasswordHint() {
		return passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public String getSecurity_ques_1() {
		return security_ques_1;
	}

	public void setSecurity_ques_1(String security_ques_1) {
		this.security_ques_1 = security_ques_1;
	}

	public String getSecurity_ans_1() {
		return security_ans_1;
	}

	public void setSecurity_ans_1(String security_ans_1) {
		this.security_ans_1 = security_ans_1;
	}

	public String getSecurity_ques_2() {
		return security_ques_2;
	}

	public void setSecurity_ques_2(String security_ques_2) {
		this.security_ques_2 = security_ques_2;
	}

	public String getSecurity_ans_2() {
		return security_ans_2;
	}

	public void setSecurity_ans_2(String security_ans_2) {
		this.security_ans_2 = security_ans_2;
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}


    @JsonIgnore
	public String getPassword() {
	return this.password;
	}
    @JsonProperty
    public void setPassword(String password) {
    	this.password=password;
    }
}
