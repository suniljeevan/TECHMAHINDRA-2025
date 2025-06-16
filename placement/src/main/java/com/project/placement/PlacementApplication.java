package com.project.placement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.project")  // Adjusted to your PMS package
@EnableJpaRepositories(basePackages = "com.project.repository")  // Adjusted for your repository package
@EntityScan(basePackages = "com.project.model")  // Adjusted for your model package
@SpringBootApplication(scanBasePackages = "com.project")  // Adjusted to the main PMS package
public class PlacementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlacementApplication.class, args);
    }

}
