package com.ams.CONFIG;
import com.ams.MODEL.User;
import com.ams.SERVICE.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminUserConfig {

    @Bean
    public CommandLineRunner createAdminUser(UserService userService) {
        return args -> {
        	String adminEmail = "admin@gmail.com";  
        	
            if (!userService.userExists(adminEmail)) {
                User admin = new User();
                admin.setEmail(adminEmail);   
                //admin.setUsername("admin");
                admin.setPassword("admin123"); // Will be encoded by UserService
                admin.setRole("ADMIN");
                userService.saveUser(admin);
                System.out.println("Admin user created.");
            }
        };
    }
}
