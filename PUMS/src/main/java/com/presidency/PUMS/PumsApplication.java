package com.presidency.PUMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.ums")
@EnableJpaRepositories(basePackages = "com.ums.REPOSITORY")
@EntityScan(basePackages = "com.ums.MODEL")
@SpringBootApplication(scanBasePackages = "com.ums")
public class PumsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PumsApplication.class, args);
	}

}
