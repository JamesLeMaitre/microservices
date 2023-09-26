package com.esmc.demandeAchatBanKsu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class
 DemandeAchatBanKsuApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemandeAchatBanKsuApplication.class, args);
	}

	CommandLineRunner start(){
		return args -> {
		};
	}
}
