package com.tcms.entity;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user", schema="user_schema")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
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
	@Column(name="password",nullable=false,length=75)
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
