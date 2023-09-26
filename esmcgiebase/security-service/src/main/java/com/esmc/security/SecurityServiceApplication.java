package com.esmc.security;

import com.esmc.security.entity.AppUser;
import com.esmc.security.repository.AppRoleRepository;
import com.esmc.security.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableCaching
public class SecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start(AppUserRepository utilisateurRepository, AppRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		return args -> {
//            roleRepository.save(new AppRole(null, "USER"));
//            roleRepository.save(new AppRole(null, "ADMIN"));
//            roleRepository.save(new AppRole(null, "CUSTOMER_MANAGER"));
			

			if (utilisateurRepository.findAll().size() < 2) {
				utilisateurRepository.save(new AppUser(1l, "user1", passwordEncoder.encode("1234"), null, true));
				utilisateurRepository.save(new AppUser(2l,"automate_user", passwordEncoder().encode("1234"), null,true ));
			}

		};
	}

}
