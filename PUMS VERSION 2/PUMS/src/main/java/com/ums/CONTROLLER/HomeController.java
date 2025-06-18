package com.ums.CONTROLLER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage() {
        return "index"; // this will resolve to index.html inside src/main/resources/templates
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about"; // Optional: about.html if you plan to have an about page
    }
}