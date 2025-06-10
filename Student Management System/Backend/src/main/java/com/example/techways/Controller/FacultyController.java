package com.example.techways.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techways.DTO.FacultyDTO;
import com.example.techways.DTO.RequestResponse;
import com.example.techways.Service.FacultyService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    // Register Faculty
    @PostMapping("/register")
    public ResponseEntity<RequestResponse> registerFaculty(@RequestBody FacultyDTO dto) {
        return ResponseEntity.ok(facultyService.registerFaculty(dto));
    }

    // Faculty Login
    @PostMapping("/login")
    public ResponseEntity<RequestResponse> loginFaculty(@RequestBody FacultyDTO dto) {
        return ResponseEntity.ok(facultyService.loginFaculty(dto.getFacultyEmail(), dto.getFacultyPassword()));
    }

    // Refresh Token
    @PostMapping("/refresh")
    public ResponseEntity<RequestResponse> refreshToken(@RequestBody RequestResponse request) {
        return ResponseEntity.ok(facultyService.refreshToken(request));
    }

    // Get All Faculties
    @GetMapping("/get-all")
    public ResponseEntity<RequestResponse> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    // Get Faculty by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<RequestResponse> getFacultyById(@PathVariable Integer id) {
        return ResponseEntity.ok(facultyService.getFacultyById(id));
    }

    // Update Faculty
    @PutMapping("/update/{id}")
    public ResponseEntity<RequestResponse> updateFaculty(@PathVariable Integer id, @RequestBody FacultyDTO dto) {
        return ResponseEntity.ok(facultyService.updateFaculty(id, dto));
    }

    // Delete Faculty
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RequestResponse> deleteFaculty(@PathVariable Integer id) {
        return ResponseEntity.ok(facultyService.deleteFaculty(id));
    }

    // Get My Info (authenticated faculty)
    @GetMapping("/get-my-info")
    public ResponseEntity<RequestResponse> getMyInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return ResponseEntity.ok(facultyService.getFacultyInfo(email));
    }
}
