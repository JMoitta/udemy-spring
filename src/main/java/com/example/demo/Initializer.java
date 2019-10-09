package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Functionality;
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
		
		Functionality functionality = new Functionality();
		functionality.setName("Add");
		
		Functionality functionality2 = new Functionality();
		functionality2.setName("Delete");
		
		
		
		Role role = new Role("Admin", StatusRole.ATIVO, Arrays.asList(functionality));
		
		
		Role role3 = new Role("Aluno", StatusRole.ATIVO, Arrays.asList(functionality2));

		
		User user = new User();

		user.setName("Nataniel");
		user.setEmail("nataniel.paiva@gmail.com");
		user.setRoles(Arrays.asList(role, role3));

		this.userRepository.save(user);

		List<User> userR = this.userRepository.findAll();

		for (User user2 : userR) {
			for (Role role2 : user2.getRoles()) {
				System.out.println(role2.getName());
				for(Functionality f : role2.getFunctionalities()) {
					System.out.println(f.getName());
				}
			}
		}

	}

}
