package com.tcms.payloads;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcms.entity.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private Long userId;
	private String salutation;
	private String first_name;
	private String last_name;
	private String tel_code;
	private String mobile;
	private String email;
	private String username;
	private String password;
	private String passwordHint;
	private String security_ques_1;
	private String security_ans_1;
	private String security_ques_2;
	private String security_ans_2;
	private Set<RoleDto> roles=new HashSet<>();
	
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
}
