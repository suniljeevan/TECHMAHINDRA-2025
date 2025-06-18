package com.example.event_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.event_management_system.model.Admin;
import com.example.event_management_system.service.AdminService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "*")  // allow frontend access
public class AdminmanagementController {

    @Autowired
    private AdminService adminService;

    // ✅ Admin Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        // Allow default hardcoded admin
        if ("admin".equals(admin.getUserId()) && "shang".equals(admin.getPassword())) {
            return ResponseEntity.ok("Login successful");
        }

        Optional<Admin> existing = adminService.findById(admin.getUserId());
        if (existing.isPresent()) {
            Admin storedAdmin = existing.get();
            if (adminService.matchesPassword(admin.getPassword(), storedAdmin.getPassword())) {
                return ResponseEntity.ok("Login successful");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    // ✅ Create Admin (block creation of default admin)
    @PostMapping
    public ResponseEntity<?> createAdmin(@RequestBody Admin admin) {
        if ("admin".equalsIgnoreCase(admin.getUserId())) {
            return new ResponseEntity<>("Cannot create or override default admin.", HttpStatus.FORBIDDEN);
        }

        try {
            Admin savedAdmin = adminService.addAdmin(admin);
            return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Admin already exists with userId: " + admin.getUserId(), HttpStatus.CONFLICT);
        }
    }

    // ✅ Get All Admins
    @GetMapping
    public List<Admin> getAdmins() {
        return adminService.getAllAdmins();
    }

    // ✅ Update Admin (block update of default admin)
    @PutMapping
    public ResponseEntity<?> updateAdmin(@RequestBody Admin admin) {
        if ("admin".equalsIgnoreCase(admin.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Default admin cannot be updated.");
        }

        try {
            Admin updatedAdmin = adminService.updateAdmin(admin);
            return ResponseEntity.ok(updatedAdmin);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Admin not found for update.", HttpStatus.NOT_FOUND);
        }
    }

    // ✅ Delete Admin (block deletion of default admin)
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable String userId) {
        if ("admin".equalsIgnoreCase(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Default admin cannot be deleted.");
        }

        adminService.deleteAdmin(userId);
        return ResponseEntity.ok("Admin deleted successfully.");
    }
}
