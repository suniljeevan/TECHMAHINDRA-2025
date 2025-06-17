package com.admin.scholarship.controller;

import com.admin.scholarship.model.Message;
import com.admin.scholarship.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping("/contact")
    public String submitMessage(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String message) {
        System.out.println("Received: " + name + ", " + email + ", " + message); // DEBUG
        Message msg = new Message();
        msg.setName(name);
        msg.setEmail(email);
        msg.setMessage(message);
        service.saveMessage(msg);
        return "thank-you"; 
    }

    @GetMapping("/admin/messages")
    public String viewMessages(Model model) {
        model.addAttribute("messages", service.getAllMessages());
        return "admin/admin-messages"; // redirect after successful submission
    }
}
