package com.tcms.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tcms.payloads.Enquiry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user", schema="user_schema")

public class User implements UserDetails{
	@Id
	@GeneratedValue
	private Long userId;
	@Column(name="salutation")
	private String salutation;
	@Column(name="first_name",nullable=false,length=25)
	private String first_name;
	@Column(name="last_name",nullable=false,length=25)
	private String last_name;
	@Column(name="tel_code",nullable=false,length=4)
	private String tel_code;
	@Column(name="mobile",nullable=false,length=10)
	private String mobile;
	@Column(name="email",nullable=false,length=60,unique=true)
    private String email;
	@Column(name="username",nullable=false,length=25)
    private String username;
	@Column(name="password",length=75)
    private String password;
	@Column(name="passwordHint",nullable=false,length=75)
	private String passwordHint;
	@Column(name="security_ques_1",nullable=false,length=75)
    private String security_ques_1;
	@Column(name="security_ans_1",nullable=false,length=75)
	private String security_ans_1;
	@Column(name="security_ques_2",nullable=false,length=75)
    private String security_ques_2;
	@Column(name="security_ans_2",nullable=false,length=75)
	private String security_ans_2;
	
	
    public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	public User(Long userId, String salutation, String first_name, String last_name, String tel_code, String mobile,
			String email, String username, String password, String passwordHint, String security_ques_1,
			String security_ans_1, String security_ques_2, String security_ans_2, Set<Role> roles) {
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

    @Override
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


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


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	




	@Override
	public String toString() {
		return "User [userId=" + userId + ", salutation=" + salutation + ", first_name=" + first_name + ", last_name="
				+ last_name + ", tel_code=" + tel_code + ", mobile=" + mobile + ", email=" + email + ", username="
				+ username + ", password=" + password + ", passwordHint=" + passwordHint + ", security_ques_1="
				+ security_ques_1 + ", security_ans_1=" + security_ans_1 + ", security_ques_2=" + security_ques_2
				+ ", security_ans_2=" + security_ans_2 + ", roles=" + roles + "]";
	}





	@ManyToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
    schema = "user_schema",
    joinColumns =@JoinColumn(name="user_id",referencedColumnName="userId"),
    inverseJoinColumns= @JoinColumn(name="role_id",referencedColumnName="role_id")
    		)
	private Set<Role> roles=new HashSet<>();
    @Override
    public String getUsername() {
    	return this.email;
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities=this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
