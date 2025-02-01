package com.spring.ai;

import com.spring.ai.model.Role;
import com.spring.ai.model.User;
import com.spring.ai.repository.RoleRepository;
import com.spring.ai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringaiApplication  implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringaiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Role adminRole = new Role("ADMIN");
		Role userRole = new Role("USER");


		if(roleRepository.findAll().isEmpty()){
			// Create sample roles

			roleRepository.saveAll(List.of(adminRole, userRole));
		}

		if(userRepository.findAll().isEmpty()){
			// Create sample users
			User user1 = new User("john", "john@example.com");
			User user2 = new User("jane", "jane@example.com");
			user1.getRoles().add(adminRole);
			user2.getRoles().add(userRole);
			userRepository.saveAll(List.of(user1, user2));
		}


	}

}
