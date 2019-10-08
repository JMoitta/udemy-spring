package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		Role role = new Role("Admin", StatusRole.ATIVO);
		
//		this.roleRepository.save(role);
		
		User user = new User();
		
		user.setName("Nataniel");
		user.setEmail("nataniel.paiva@gmail.com");
		user.setRole(role);
		
		this.userRepository.save(user);
		
		List<User> userR = this.userRepository.findAll();
		
		for(User user2 : userR) {
			System.out.println(user2.getRole());
		}
	}

}
