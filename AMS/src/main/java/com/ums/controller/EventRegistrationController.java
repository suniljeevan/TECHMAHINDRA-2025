package com.ums.controller;

import com.ums.model.AlumniEvent;
import com.ums.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.UUID;

@Controller
public class EventRegistrationController {

    @Autowired
    private EventService eventService;
/*
    // Admin posts a new event
    @PostMapping("/admin/events/save")
    public String saveEvent(@ModelAttribute AlumniEvent event) {
        // Auto-generate UUID if not provided
        if (event.getEventId() == null || event.getEventId().isEmpty()) {
        	throw new IllegalArgumentException("Event ID is required.");
        }

        eventService.insertEvent(event);
        return "redirect:/admin/dashboard";
    }
*/
    
    // Fetch all events for alumni dashboard
    @GetMapping("/alumni/events")
    @ResponseBody
    public List<AlumniEvent> fetchAllEvents() {
        return eventService.fetchAllEvents();
    }


    // (Optional) Admin can delete an event
    @PostMapping("/admin/events/delete")
    public String deleteEvent(@RequestParam("eventId") String eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/admin/dashboard";
    }

    // (Optional) Admin can fetch an event for editing
    @GetMapping("/admin/events/{eventId}")
    @ResponseBody
    public AlumniEvent getEventById(@PathVariable String eventId) {
        return eventService.fetchEventById(eventId);
    }
}
