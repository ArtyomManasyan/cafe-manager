package com.cafeManagerAssignment.cafeManager;

import com.cafeManagerAssignment.cafeManager.model.User;
import com.cafeManagerAssignment.cafeManager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CafeManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeManagerApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        return args -> {
            User manager = new User();
            manager.setFirstName("Mihran");
            manager.setLastName("Bezjyan");
            manager.setUsername("manager");
            manager.setPassword(encoder.encode("password"));
            manager.setManager(true);

            userRepository.save(manager);
        };
    }

}
