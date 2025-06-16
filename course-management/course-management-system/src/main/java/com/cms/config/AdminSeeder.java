package com.cms.config;

import com.cms.entity.CustomUser;
import com.cms.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.UUID;

@Configuration
public class AdminSeeder {

    @Bean
    public CommandLineRunner createAdmin(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            String adminEmail = "admin@cms.com";

            if (userRepository.findByEmail(adminEmail) == null) {
                CustomUser admin = new CustomUser();
                admin.setId(UUID.randomUUID().toString());
                admin.setFirstName("Admin");
                admin.setLastName("User");
                admin.setEmail(adminEmail);
                admin.setPhone("9999999999");
                admin.setPassword(passwordEncoder.encode("admin123")); // Replace with a strong password in production
                admin.setRole("ADMIN");
                admin.setCreateDate(new Date());

                userRepository.save(admin);
                System.out.println("Admin user created successfully.");
            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }
}
