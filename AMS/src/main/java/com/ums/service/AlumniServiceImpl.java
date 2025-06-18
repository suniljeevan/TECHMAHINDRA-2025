package com.ums.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import com.ums.model.ActivityLog;
import com.ums.model.Admin;
import com.ums.model.Alumni;
import com.ums.repository.AlumniRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AlumniServiceImpl implements AlumniService {

    @Autowired
    private AlumniRepository alumniRepo;
    
    @Autowired
    private ActivityLogService activityLogService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    @Autowired
    private HttpSession session;
    
    @Override
    public Alumni insertAlumni(Alumni a) {
    	Alumni saved = alumniRepo.save(a);// JPA save() method
        
        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin != null) {
            ActivityLog log = new ActivityLog();
            log.setAdminId(admin.getAdminId());
            log.setAction("Added new Alumni");
            log.setEntityType("Alumni");
            log.setEntityId(String.valueOf(saved.getAlumniId()));
            log.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            activityLogService.insertLog(log);
        }

        return saved;
    }

    @Override
    public Alumni updateAlumni(Alumni a, int id) {
        Optional<Alumni> optionalAlumni = alumniRepo.findById(id);
        if (optionalAlumni.isPresent()) {
            Alumni existingAlumni = optionalAlumni.get();
            existingAlumni.setName(a.getName());
            existingAlumni.setUsername(a.getUsername());
            existingAlumni.setEmail(a.getEmail());
            if (a.getPassword() != null && !a.getPassword().isBlank()) {
                existingAlumni.setPassword(a.getPassword()); // already encoded
            }
            existingAlumni.setGraduationYear(a.getGraduationYear());
            existingAlumni.setUniversityId(a.getUniversityId());
            existingAlumni.setPasswordChangeRequired(a.isPasswordChangeRequired());
            existingAlumni.setCompanyName(a.getCompanyName());
            existingAlumni.setJobTitle(a.getJobTitle());
            if (a.getImageUrl() != null && !a.getImageUrl().isBlank()) {
                existingAlumni.setImageUrl(a.getImageUrl());
            }
            existingAlumni.setBranch(a.getBranch());


            Alumni updated = alumniRepo.save(existingAlumni);

            Admin admin = (Admin) session.getAttribute("loggedInAdmin");
            if (admin != null) {
                ActivityLog log = new ActivityLog();
                log.setAdminId(admin.getAdminId());
                log.setAction("Updated Alumni");
                log.setEntityType("Alumni");
                log.setEntityId(String.valueOf(updated.getAlumniId()));
                log.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
                activityLogService.insertLog(log);
            }

            return updated;
        } else {
            throw new RuntimeException("Alumni not found with ID: " + id);
        }
    }


    @Override
    public void deleteAlumni(int id) {
        alumniRepo.deleteById(id);

        Admin admin = (Admin) session.getAttribute("loggedInAdmin");
        if (admin != null) {
            ActivityLog log = new ActivityLog();
            log.setAdminId(admin.getAdminId());
            log.setAction("Deleted Alumni");
            log.setEntityType("Alumni");
            log.setEntityId(String.valueOf(id));
            log.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            activityLogService.insertLog(log);
        }
    }


    @Override
    public List<Alumni> fetchAllAlumni() {
        return alumniRepo.findAll();
    }

    @Override
    public Alumni fetchAlumniById(int id) {
        return alumniRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumni not found with ID: " + id));
    }

    @Override
    public Alumni fetchAlumniByEmail(String email) {
        return alumniRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Alumni not found with email: " + email));
    }

    @Override
    public Alumni fetchAlumniByUsername(String username) {
        return alumniRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Alumni not found with username: " + username));
    }
    
    @Override
    public Alumni authenticate(String username, String password) {
        Optional<Alumni> optionalAlumni = alumniRepo.findByUsername(username);
        if (optionalAlumni.isPresent()) {
            Alumni alumni = optionalAlumni.get();
            if (passwordEncoder.matches(password, alumni.getPassword())) {
                return alumni;
            }
        }
        return null;
    }
}
