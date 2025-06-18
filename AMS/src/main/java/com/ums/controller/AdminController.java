package com.ums.controller;

import com.ums.model.Admin;
import com.ums.model.Alumni;
import com.ums.model.AlumniEvent;
import com.ums.model.Message;
import com.ums.model.ActivityLog;
import com.ums.service.AdminService;
import com.ums.service.AlumniService;
import com.ums.service.EventService;
import com.ums.service.MessageService;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import com.ums.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AlumniService alumniService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ActivityLogService activityLogService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageService messageService;

    // =======================
    // 1. ADMIN AUTH / PROFILE
    // =======================
    
    // 1.1 Admin Dashboard
    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        System.out.println("Admin in session: " + (admin == null ? "null" : admin.getUsername()));
        if (admin == null) return "redirect:/homepage";

        model.addAttribute("adminId", admin.getAdminId());
        model.addAttribute("admin", admin);
        return "AdminPage";
    }

    // 1.2 Change Admin Password (AJAX)
    @PostMapping("/admin/change-password")
    @ResponseBody
    public ResponseEntity<String> changeAdminPassword(@RequestBody Map<String, String> payload, HttpSession session) {
        try {
            Admin admin = (Admin) session.getAttribute("loggedInAdmin");
            if (admin == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");

            String newPassword = payload.get("newPassword");
            if (newPassword == null || newPassword.length() < 4)
                return ResponseEntity.badRequest().body("Password too short");

            admin.setPassword(passwordEncoder.encode(newPassword));
            adminService.updateAdmin(admin, admin.getAdminId());
            session.setAttribute("loggedInAdmin", admin);

            return ResponseEntity.ok("Password updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error updating password: " + e.getMessage());
        }
    }

    // 1.3 Update Admin Profile (AJAX)
    @PostMapping("/admin/update-profile")
    @ResponseBody
    public ResponseEntity<String> updateAdminProfile(@RequestBody Admin updatedAdmin, HttpSession session) {
        Admin loggedInAdmin = (Admin) session.getAttribute("loggedInAdmin");
        if (loggedInAdmin == null) return ResponseEntity.status(401).body("Unauthorized: Please login.");

        loggedInAdmin.setFullName(updatedAdmin.getFullName());
        loggedInAdmin.setUsername(updatedAdmin.getUsername());
        //loggedInAdmin.setPassword(updatedAdmin.getPassword());

        try {
            adminService.updateAdmin(loggedInAdmin, loggedInAdmin.getAdminId());
            session.setAttribute("loggedInAdmin", loggedInAdmin);
            return ResponseEntity.ok("Admin profile updated successfully.");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body("Update failed: " + ex.getMessage());
        }
    }

    // =======================
    // 2. ALUMNI MANAGEMENT
    // =======================

    // 2.1 Fetch All Alumni (JSON API)
    @GetMapping("/admin/alumni/fetch")
    public List<Alumni> fetchAllAlumni() {
        return alumniService.fetchAllAlumni();
    }

    // 2.2 Add New Alumni
    @PostMapping("/admin/alumni/add")
    public String addAlumni(@ModelAttribute Alumni alumni) {
        if (alumni.getImageUrl() == null || alumni.getImageUrl().isBlank()) {
            alumni.setImageUrl("/images/default_alumni.png");
        }
        alumni.setPasswordChangeRequired(false);
        alumniService.insertAlumni(alumni);
        return "redirect:/admin/dashboard";
    }

    // 2.3 View Alumni Directory (HTML)
    @GetMapping("/admin/alumni/view")
    public String viewAlumniDirectory(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin == null) return "redirect:/AdminLogin";

        model.addAttribute("admin", admin);
        model.addAttribute("allAlumni", alumniService.fetchAllAlumni());
        return "AdminAlumniDirectory";
    }

    // 2.4 Update Alumni
    @PostMapping("/admin/alumni/update")
    public String updateAlumni(@ModelAttribute Alumni alumni) {
        Alumni existing = alumniService.fetchAlumniById(alumni.getAlumniId());

        existing.setName(alumni.getName());
        existing.setEmail(alumni.getEmail());
        existing.setUsername(alumni.getUsername());
        existing.setGraduationYear(alumni.getGraduationYear());
        existing.setBranch(alumni.getBranch());
        existing.setCompanyName(alumni.getCompanyName());
        existing.setJobTitle(alumni.getJobTitle());

        if (alumni.getImageUrl() != null && !alumni.getImageUrl().isBlank()) {
            existing.setImageUrl(alumni.getImageUrl());
        }

        existing.setPassword(alumni.getPassword() != null ? alumni.getPassword() : existing.getPassword());
        existing.setUniversityId(existing.getUniversityId());
        existing.setPasswordChangeRequired(existing.isPasswordChangeRequired());

        alumniService.updateAlumni(existing, existing.getAlumniId());
        return "redirect:/admin/alumni/view";
    }

    // 2.5 Delete Alumni
    @PostMapping("/admin/alumni/delete")
    public String deleteAlumniViaForm(@RequestParam("alumniId") int id) {
        alumniService.deleteAlumni(id);
        return "redirect:/admin/dashboard";
    }

    // =======================
    // 3. MESSAGING
    // =======================

    // 3.1 Admin Inbox: View received messages
    @GetMapping("/admin/messages")
    public String viewAdminInbox(HttpSession session, Model model, HttpServletRequest request) {
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin == null) return "redirect:/AdminLogin";

        List<Message> inbox = messageService.getMessagesSentByAdmin(admin.getAdminId());
        model.addAttribute("admin", admin); 
        model.addAttribute("adminId", admin.getAdminId());
        model.addAttribute("messages", inbox);
     
        return "AdminPage";
    }

    // 3.2 Send message to alumni
    @PostMapping("/admin/messages/send")
    public String sendMessageToAlumni(@RequestParam int alumniId,
                                      @RequestParam String subject,
                                      @RequestParam String content,
                                      HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin == null) return "redirect:/AdminLogin";

        Message message = new Message();
        message.setSenderId(admin.getAdminId());
        message.setSenderType("ADMIN");
        message.setRecipientId(alumniId);
        message.setRecipientType("ALUMNI");
        message.setSubject(subject);
        message.setContent(content);

        messageService.saveMessage(message);
        return "redirect:/admin/messages";
    }
    
 // 3.3 API - Get messages received by admin (from alumni)
    @GetMapping("/api/messages/received/{adminId}")
    @ResponseBody
    public List<Message> getMessagesReceivedByAdmin(@PathVariable int adminId) {
        return messageService.getMessagesForAdmin(adminId);
    }

    // 3.4 API - Get messages sent by admin (to alumni)
    @GetMapping("/api/messages/sent/{adminId}")
    @ResponseBody
    public List<Message> getMessagesSentByAdmin(@PathVariable int adminId) {
        return messageService.getMessagesSentByAdmin(adminId);
    }


    // =======================
    // 4. EVENT MANAGEMENT
    // =======================

    // 4.1 Save new event
    @PostMapping("/admin/events/save")
    public String saveEvent(@ModelAttribute AlumniEvent event, HttpSession session) {
        if (event.getEventId() == null || event.getEventId().isBlank()) {
            throw new IllegalArgumentException("Event ID is required.");
        }

        eventService.insertEvent(event);
        session.setAttribute("showViewEventsSection", true);
        return "redirect:/admin/dashboard";
    }

    // 4.2 View all events
    @GetMapping("/admin/events/view")
    public String viewEventsPage(HttpSession session, Model model) {
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin == null) return "redirect:/AdminLogin";

        model.addAttribute("admin", admin);
        model.addAttribute("events", eventService.fetchAllEvents());
        return "AdminViewEvents";
    }

    // 4.3 Edit event form
    @GetMapping("/admin/edit-event/{eventId}")
    public String showEditEventForm(@PathVariable String eventId, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin == null) return "redirect:/AdminLogin";

        AlumniEvent event = eventService.fetchEventById(eventId);
        model.addAttribute("event", event);
        model.addAttribute("admin", admin);
        return "AdminViewEvents";
    }
    
    // 4.4 Delete event
    @PostMapping("/admin/delete-event")
    public String deleteEvent(@RequestParam String eventId, HttpSession session) {
        eventService.deleteEvent(eventId);

        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin != null) {
            ActivityLog log = new ActivityLog();
            log.setAdminId(admin.getAdminId());
            log.setAction("Deleted event with ID: " + eventId);
            log.setEntityType("AlumniEvent");
            log.setEntityId(eventId);
            log.setTimestamp(new Timestamp(System.currentTimeMillis()));
            activityLogService.insertLog(log);
        }

        return "redirect:/admin/events/view";
    }
    
    @PostMapping("/admin/update-event")
    public String updateEvent(@ModelAttribute AlumniEvent updatedEvent, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin == null) return "redirect:/AdminLogin";

        AlumniEvent existing = eventService.fetchEventById(updatedEvent.getEventId());
        if (existing == null) throw new IllegalArgumentException("Event not found with ID: " + updatedEvent.getEventId());

        existing.setTitle(updatedEvent.getTitle());
        existing.setDescription(updatedEvent.getDescription());
        existing.setVenue(updatedEvent.getVenue());
        existing.setEventDate(updatedEvent.getEventDate());
        existing.setEventTime(updatedEvent.getEventTime());
        // Add imageUrl if it's present in the modal in future
        // existing.setImageUrl(updatedEvent.getImageUrl());

        eventService.updateEvent(existing, existing.getEventId());

        // Optional: log the action
        ActivityLog log = new ActivityLog();
        log.setAdminId(admin.getAdminId());
        log.setAction("Updated event with ID: " + updatedEvent.getEventId());
        log.setEntityType("AlumniEvent");
        log.setEntityId(updatedEvent.getEventId());
        log.setTimestamp(new Timestamp(System.currentTimeMillis()));
        activityLogService.insertLog(log);

        return "redirect:/admin/events/view";
    }


    // =======================
    // 5. ACTIVITY LOGS
    // =======================

    // 5.1 View admin logs (API)
    @GetMapping("/admin/logs")
    public List<ActivityLog> viewActivityLogs() {
        return activityLogService.fetchLogsByAdmin(1); // Adjust to dynamic admin ID later
    }
}
