package com.tcms.services;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.tcms.entity.User;
import com.tcms.repository.UserRepository;
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(username);
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
       // return org.springframework.security.core.userdetails.User
                //.withUsername(user.getUsername())
               // .password(user.getPassword())
                //.authorities("ROLE_USER") // You may modify this according to your authorization logic
               // .accountExpired(false)
               // .accountLocked(false)
               // .credentialsExpired(false)
               // .disabled(false)
               // .build();
		return user;
		
	}

}
