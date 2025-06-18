package com.example.event_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.event_management_system.model.Student;
import com.example.event_management_system.service.StudenteventService;

@Controller
public class event_registration_controller {

	@GetMapping("/event-registration")
    public String showevent() {
        return "event_registration";  // This returns admin.html from /templates
    }
	
}
