package com.api.protecaoanimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProtecaoAnimalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtecaoAnimalApplication.class, args);
		System.out.println( "Gerador de senha: "+new BCryptPasswordEncoder().encode("admin"));
	}

}