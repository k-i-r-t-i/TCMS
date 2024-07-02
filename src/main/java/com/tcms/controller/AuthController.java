package com.tcms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;
import com.tcms.entity.User;
import com.tcms.exceptions.ApiException;
import com.tcms.helper.JwtUtil;
import com.tcms.model.JwtRequest;
import com.tcms.model.JwtResponse;
import com.tcms.payloads.UserDto;
import com.tcms.services.CustomUserDetailsService;
import com.tcms.services.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserService userService;
	@Autowired
    private ModelMapper mapper;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> createToken(@RequestBody JwtRequest request) throws Exception{
		this.authenticate(request.getUsername(),request.getPassword());

		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		
		JwtResponse response=new JwtResponse();
		response.setToken(token);
		System.out.println("JWT " + token);
		response.setUser(this.mapper.map((User)userDetails,UserDto.class));
		//return ResponseEntity.ok(new JwtResponse(token));
		return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);
	}
	private void authenticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("password"+password);
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
		try {
		this.authenticationManager.authenticate(authenticationToken);
		}catch(BadCredentialsException e){
			e.printStackTrace();
			System.out.println("Invalid Details!!");
			throw new ApiException("invalid username or password");
		}
		
	}
	//register new user api
	@PostMapping("/register")
	public ResponseEntity<UserDto>registerUser(@RequestBody UserDto userDto)
	{   
	    System.out.println("userdto===="+userDto.toString());
		UserDto registeredUser=this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	}

}

