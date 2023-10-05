package com.urban.p2pchatapp;

import com.urban.p2pchatapp.models.User;
import com.urban.p2pchatapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class P2PchatAppApplication implements CommandLineRunner {

	private final UserService userService;

	@Autowired
	public P2PchatAppApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(P2PchatAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userService.addNewUser(new User("admin"));

	}
}
