package com.tcms.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tcms.payloads.UserDto;
public interface UserService {
	UserDto registerNewUser(UserDto user);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Long userId);
	UserDto getUserById(Long userId);
	List<UserDto> getAllUsers();
	void deleteUser(Long userId);
}
