package com.tcms.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tcms.config.AppConstants;
import com.tcms.entity.Role;
import com.tcms.entity.User;
import com.tcms.exceptions.ResourceNotFoundException;
import com.tcms.payloads.Enquiry;
import com.tcms.payloads.UserDto;
import com.tcms.repository.RoleRepository;
import com.tcms.repository.UserRepository;
import com.tcms.services.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
    private ModelMapper modelMapper;
	@Autowired
	private RestTemplate restTemplate;
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	/*@Override
	 public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepository.save(user);
		return this.userToDto(savedUser);
	}*/
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=this.modelMapper.map(userDto, User.class);
		User savedUser=this.userRepository.save(user);
		return this.modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {
		// TODO Auto-generated method stub
		User user=this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		user.setSalutation(userDto.getSalutation());
		user.setFirst_name(userDto.getFirst_name());
		user.setLast_name(userDto.getLast_name());
		user.setTel_code(userDto.getTel_code());
		user.setMobile(userDto.getMobile());
		user.setEmail(userDto.getEmail());
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setPasswordHint(userDto.getPasswordHint());
		user.setSecurity_ques_1(userDto.getSecurity_ques_1());
		user.setSecurity_ans_1(userDto.getSecurity_ans_1());
		user.setSecurity_ques_2(userDto.getSecurity_ques_2());
		user.setSecurity_ans_2(userDto.getSecurity_ans_2());
		User updatedUser=this.userRepository.save(user);
		return this.modelMapper.map(updatedUser, UserDto.class);
	}

	/*@Override
	public UserDto getUserById(Long userId) {
		//get user from database with the help of user repository
		User user=this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		//fetch enquiry of the above user from enquiry service
		//http://localhost:8082/api/enquiries/users/46
		ArrayList enquiriesOfUser=restTemplate.getForObject("http://localhost:8082/api/enquiries/users/46",ArrayList.class);
		logger.info("{}",enquiriesOfUser);
		//user.setEnquiries(enquiriesOfUser);
		UserDto userDto=this.userToDto(user);
		return userDto;
	}*/
	@Override
	public UserDto getUserById(Long userId) {
		// TODO Auto-generated method stub
		 User user=this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","user id",userId));
		   UserDto userDto=this.modelMapper.map(user, UserDto.class);
		 return userDto;
	}


	/*@Override
	public List<UserDto> getAllUsers() {
		List<User> users= this.userRepository.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}*/
	@Override
	public List<UserDto> getAllUsers() {
		List<User>users =this.userRepository.findAll();
		List<UserDto> userDtos=users.stream().map((user)->this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}

	/*@Override
	public void deleteUser(Long userId) {
		User user=this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userRepository.delete(user);
	}*/
	@Override
	public void deleteUser(Long userId) {
		User user=this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userRepository.delete(user);
	}
	/*public User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		return user;
	}
	public UserDto userToDto(User user) {
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		return userDto;
	}*/

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		//encoded the password
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//we will set the roles to user as normal user
		Role role=this.roleRepository.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser=this.userRepository.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

}
