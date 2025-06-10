package com.example.techways.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Controller";
    }
    
    @GetMapping("/contact")
    public String contact() {
        return "Welcome to the contact page";
    }

    

}
