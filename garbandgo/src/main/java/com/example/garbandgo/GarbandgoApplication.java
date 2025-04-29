package com.example.garbandgo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GarbandgoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GarbandgoApplication.class, args);
	}




	@Bean
	CommandLineRunner run(BCryptPasswordEncoder encoder) {
		return args -> {
			String encoded = encoder.encode("password");
			System.out.println("Encoded password: " + encoded);
		};
	}

}
