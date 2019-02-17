package com.caixa.notaderelease.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.caixa.notaderelease.api.repository.UserRepository;
import com.caixa.notaderelease.api.model.User;
import com.caixa.notaderelease.api.enums.ProfileEnum;

//import com.caixa.notaderelease.api.config.property.NotaDeReleaseApiProperty;

@SpringBootApplication
public class NotaDeReleaseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotaDeReleaseApiApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            initUsers(userRepository, passwordEncoder);
        };

    }
    
	private void initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        User admin = new User();
        admin.setMatricula("c0");
        admin.setNome("Administrador");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setProfile(ProfileEnum.ROLE_ADMIN);
        User find = userRepository.findByMatricula("c0");
        if (find == null) {
            userRepository.save(admin);
        }
        
        User custumer = new User();
        custumer.setMatricula("c1");
        custumer.setNome("Cliente");
        custumer.setPassword(passwordEncoder.encode("123"));
        custumer.setProfile(ProfileEnum.ROLE_CUSTOMER);
        User find2 = userRepository.findByMatricula("c1");
        if (find2 == null) {
            userRepository.save(custumer);
        }
        
        User technician = new User();
        technician.setMatricula("c2");
        technician.setNome("Tecnico");
        technician.setPassword(passwordEncoder.encode("123"));
        technician.setProfile(ProfileEnum.ROLE_TECHNICIAN);
        User find3 = userRepository.findByMatricula("c2");
        if (find3 == null) {
            userRepository.save(technician);
        }
        
        
        
    }
	
}
