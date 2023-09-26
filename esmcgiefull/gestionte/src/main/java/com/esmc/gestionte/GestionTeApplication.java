package com.esmc.gestionte;

import com.esmc.gestionte.entities.Etiquette;
import com.esmc.gestionte.repositories.EtiquetteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


import java.util.Date;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class   GestionTeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionTeApplication.class, args);
	}

	@Bean
	CommandLineRunner start(EtiquetteRepository etiquetteRepository) {
		return args -> {

			if (etiquetteRepository.findAll().size() < 2) {
				Date current = new Date();
				etiquetteRepository.save(new Etiquette(1l, "Appro", true,current, current));
				etiquetteRepository.save(new Etiquette(2l,"Re-Appro", true, current,current ));
				etiquetteRepository.save(new Etiquette(3l, "Marge", true,current, current));
				etiquetteRepository.save(new Etiquette(4l,"Principale", true, current,current ));
			}

		};
	}

}

