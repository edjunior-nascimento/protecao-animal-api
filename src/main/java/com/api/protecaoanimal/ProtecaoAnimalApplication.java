package com.api.protecaoanimal;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProtecaoAnimalApplication {

	private static final Logger LOGGER = Logger.getLogger(ProtecaoAnimalApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(ProtecaoAnimalApplication.class, args);
		LOGGER.log(Level.INFO, "Gerador de senha: " , new BCryptPasswordEncoder().encode("admin"));
	}

}