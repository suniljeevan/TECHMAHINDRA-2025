package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        System.out.println("Home page requested");
        return "index";  // This should match your index.html file inside the templates folder
    }
    
    
}
