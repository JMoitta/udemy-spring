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
		
		User user2 = new User();
		
		user2.setName("João");
		user2.setEmail("joao@gmail.com");
//		user2.setRoles(Arrays.asList(role, role3));
		
		userRepository.save(user2);
		
		List<User> listUsers = userRepository.findByName("o");
		
		for(User user3 : listUsers) {
			System.out.println(user3.getName());
		}


	}

}
