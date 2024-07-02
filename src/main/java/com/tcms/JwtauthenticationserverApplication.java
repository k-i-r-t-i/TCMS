package com.tcms;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.tcms.config.AppConstants;
import com.tcms.entity.Role;
import com.tcms.repository.RoleRepository;
@EnableEurekaClient
@SpringBootApplication
public class JwtauthenticationserverApplication implements CommandLineRunner{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    
	public static void main(String[] args) {
		SpringApplication.run(JwtauthenticationserverApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.passwordEncoder.encode("kirti"));
		System.out.println(this.passwordEncoder.encode("coco"));
		 try {
		        Role role = new Role();
		        role.setRole_id(AppConstants.ADMIN_USER);
		        role.setName("ADMIN_USER");
		        Role role1 = new Role();
		        role1.setRole_id(AppConstants.NORMAL_USER);
		        role1.setName("NORMAL_USER");
		        List<Role> roles = Arrays.asList(role, role1);
		        List<Role> result = this.roleRepository.saveAll(roles);
		        result.forEach(r -> {
		            System.out.println(r.getName());
		        });
		    } catch (Exception e) {
		    	e.printStackTrace();

		    }
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
