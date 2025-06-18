package com.example.event_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexcontroller {

	@GetMapping("/index")
	public String showindex() {
	    return "index"; // loads index.html from templates
	}
}
