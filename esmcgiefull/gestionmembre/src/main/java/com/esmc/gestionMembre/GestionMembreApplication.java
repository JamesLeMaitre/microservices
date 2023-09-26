package com.esmc.gestionMembre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class  GestionMembreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionMembreApplication.class, args);
	}
	
}
