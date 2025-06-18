package com.ums.controller;

import com.ums.model.Admin;
import com.ums.model.Alumni;
import com.ums.model.AlumniEvent;
import com.ums.model.EventRegistration;
import com.ums.model.Message;
import com.ums.service.AdminService;
import com.ums.service.AlumniService;
import com.ums.service.EventService;
import com.ums.service.MessageService;
import com.ums.service.EventRegistrationService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.List;
@Controller
public class AlumniController {

    @Autowired
    private AlumniService alumniService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRegistrationService eventRegistrationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageService messageService;
    
    @Autowired
    private AdminService adminService;

    // ==========================
    // 1. AUTH / PROFILE
    // ==========================

    // 1.1 Redirect /alumni → dashboard
    @GetMapping("/alumni")
    public String redirectToDashboard() {
        return "redirect:/Alumni/dashboard";
    }

    // 1.2 Alumni dashboard
    @GetMapping("/Alumni/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Alumni alumni = (Alumni) session.getAttribute("loggedInAlumni");
        if (alumni == null) return "redirect:/AlumniLogin";

        model.addAttribute("alumni", alumni);
        model.addAttribute("events", eventService.fetchAllEvents());
        model.addAttribute("registeredEvents", eventService.getEventsRegisteredByAlumni(alumni.getAlumniId()));
        model.addAttribute("allAlumni", alumniService.fetchAllAlumni());
        List<Admin> admins = adminService.fetchAllAdmins();
        model.addAttribute("admins", admins); 
      

        return "AlumniPage";
    }

    // 1.3 Show Change Password form
    @GetMapping("/alumni/changepassword")
    public String showChangePasswordForm(HttpSession session) {
        Alumni alumni = (Alumni) session.getAttribute("loggedInAlumni");
        return (alumni == null) ? "redirect:/AlumniLogin" : "ChangePassword";
    }

    // 1.4 Handle Password Change
    @PostMapping("/alumni/changepassword")
    public String changePassword(@RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 HttpSession session,
                                 Model model) {
        Alumni alumni = (Alumni) session.getAttribute("loggedInAlumni");
        if (alumni == null) return "redirect:/AlumniLogin";

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "ChangePassword";
        }

        alumni.setPassword(passwordEncoder.encode(newPassword));
        alumni.setPasswordChangeRequired(false);
        alumniService.updateAlumni(alumni, alumni.getAlumniId());
        session.setAttribute("loggedInAlumni", alumni);
        return "redirect:/Alumni/dashboard";
    }

    // 1.5 Update Alumni Profile
    @PostMapping("/alumni/update")
    public String updateAlumniProfile(@RequestParam String name,
                                      @RequestParam String email,
                                      @RequestParam String username,
                                      @RequestParam int graduationYear,
                                      @RequestParam String branch,
                                      @RequestParam String companyName,
                                      @RequestParam String jobTitle,
                                      @RequestParam(value = "image", required = false) MultipartFile image,
                                      HttpSession session) {
        Alumni alumni = (Alumni) session.getAttribute("loggedInAlumni");
        if (alumni == null) return "redirect:/AlumniLogin";

        alumni.setName(name);
        alumni.setEmail(email);
        alumni.setUsername(username);
        alumni.setGraduationYear(graduationYear);
        alumni.setBranch(branch);
        alumni.setCompanyName(companyName);
        alumni.setJobTitle(jobTitle);

        try {
            if (image != null && !image.isEmpty()) {
                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                File uploadPath = new File(uploadDir);
                if (!uploadPath.exists()) uploadPath.mkdirs();

                String fileName = "alumni_" + alumni.getAlumniId() + "_" + image.getOriginalFilename();
                File dest = new File(uploadPath + "/" + fileName);
                image.transferTo(dest);

                alumni.setImageUrl("/uploads/" + fileName);
            } else if (alumni.getImageUrl() == null || alumni.getImageUrl().isBlank()) {
                alumni.setImageUrl("/images/default_alumni.png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        alumniService.updateAlumni(alumni, alumni.getAlumniId());
        session.setAttribute("loggedInAlumni", alumni);
        return "redirect:/Alumni/dashboard";
    }

    // ==========================
    // 2. EVENTS
    // ==========================

    // 2.1 View all events
    @GetMapping("/alumni/view-events")
    public String showEventsPage(HttpSession session, Model model) {
        Alumni alumni = (Alumni) session.getAttribute("loggedInAlumni");
        if (alumni == null) return "redirect:/AlumniLogin";

        model.addAttribute("events", eventService.fetchAllEvents());
        return "AlumniViewEvents";
    }

    // 2.2 View registered events
    @GetMapping("/alumni/registered-events")
    public String showRegisteredEventsPage(HttpSession session, Model model) {
        Alumni alumni = (Alumni) session.getAttribute("loggedInAlumni");
        if (alumni == null) return "redirect:/AlumniLogin";

        List<AlumniEvent> registeredEvents = eventService.getEventsRegisteredByAlumni(alumni.getAlumniId());
        model.addAttribute("alumni", alumni);
        model.addAttribute("registeredEvents", registeredEvents);
        return "AlumniRegisteredEvents";
    }

    // 2.3 Register for event
    @PostMapping("/alumni/events/register")
    public String registerForEvent(@RequestParam String eventId,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {
        Alumni alumni = (Alumni) session.getAttribute("loggedInAlumni");
        if (alumni == null) return "redirect:/AlumniLogin";

        boolean registered = eventService.registerAlumniForEvent(alumni.getAlumniId(), eventId);

        if (registered) {
            redirectAttributes.addFlashAttribute("success", "You have registered for this event.");
        } else {
            redirectAttributes.addFlashAttribute("info", "You already registered for this event.");
        }

        return "redirect:/alumni/view-events";
    }

    // ==========================
    // 3. DIRECTORY
    // ==========================

    // 3.1 View alumni directory
    @GetMapping("/alumni/directory")
    public String showAlumniDirectory(Model model, HttpSession session) {
        Alumni alumni = (Alumni) session.getAttribute("loggedInAlumni");
        if (alumni == null) return "redirect:/AlumniLogin";

        model.addAttribute("alumni", alumni);
        model.addAttribute("allAlumni", alumniService.fetchAllAlumni());
        return "AlumniDirectory";
    }

    // ==========================
    // 4. MESSAGING
    // ==========================

    // 4.1 View inbox (messages received from admin)
    @GetMapping("/alumni/messages")
    public String viewMessages(HttpSession session, Model model) {
        Alumni alumni = (Alumni) session.getAttribute("loggedInAlumni");
        if (alumni == null) return "redirect:/AlumniLogin";

        model.addAttribute("messages", messageService.getMessagesForAlumni(alumni.getAlumniId()));
        return "AlumniPage";
    }

    // 4.2 Send message to admin
    @PostMapping("/alumni/messages/send")
    @ResponseBody
    public ResponseEntity<String> sendMessageToAdmin(@RequestParam int adminId,
                                                     @RequestParam String subject,
                                                     @RequestParam String content,
                                                     HttpSession session) {
        Alumni alumni = (Alumni) session.getAttribute("loggedInAlumni");
        if (alumni == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }

        Message message = new Message();
        message.setSenderId(alumni.getAlumniId());
        message.setSenderType("ALUMNI");
        message.setRecipientId(adminId);
        message.setRecipientType("ADMIN");
        message.setSubject(subject);
        message.setContent(content);

        messageService.saveMessage(message);
        return ResponseEntity.ok("Message sent");
    }

    
    @GetMapping("/api/messages/received-alumni/{alumniId}")
    @ResponseBody
    public List<Message> getMessagesReceivedByAlumni(@PathVariable int alumniId) {
        return messageService.getMessagesForAlumni(alumniId); // should filter by recipientId and recipientType == "ALUMNI"
    }


}
