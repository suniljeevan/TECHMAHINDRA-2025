package com.org.TMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@SpringBootApplication
public class TransportManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportManagementSystemApplication.class, args);
    }
}
