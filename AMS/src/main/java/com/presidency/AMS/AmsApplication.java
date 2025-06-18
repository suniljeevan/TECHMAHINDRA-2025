package com.presidency.AMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.ums")
@EnableJpaRepositories(basePackages = "com.ums.repository")
@EntityScan(basePackages = "com.ums.model")
@SpringBootApplication(scanBasePackages = "com.ums")
public class AmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmsApplication.class, args);
	}

}
