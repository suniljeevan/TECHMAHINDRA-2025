package com.example.techways.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.techways.DTO.FacultyDTO;
import com.example.techways.DTO.RequestResponse;
import com.example.techways.Models.Faculty;
import com.example.techways.Repository.FacultyRepository;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Register a new faculty
    public RequestResponse registerFaculty(FacultyDTO dto) {
        RequestResponse response = new RequestResponse();
        try {
            Faculty faculty = new Faculty();
            faculty.setName(dto.getFacultyName());
            faculty.setEmail(dto.getFacultyEmail());
            faculty.setPhone(dto.getFacultyPhone());
            faculty.setPassword(passwordEncoder.encode(dto.getFacultyRole()));
            faculty.setRole(dto.getFacultyRole());
            faculty.setDepartment(dto.getFacultyDepartment());
            faculty.setDesignation(dto.getFacultyDesignation());

            Faculty saved = facultyRepository.save(faculty);

            if (saved.getFacultyId() > 0) {
                response.setFaculty(saved);
                response.setStatusCode(200);
                response.setMessage("Faculty registered successfully");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Failed to register faculty: " + e.getMessage());
        }
        return response;
    }

    // Faculty login
    public RequestResponse loginFaculty(String email, String password) {
        RequestResponse response = new RequestResponse();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            Faculty faculty = facultyRepository.findByEmail(email).orElseThrow();
            String token = jwtUtils.generateToken(faculty);
            String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), faculty);

            response.setStatusCode(200);
            response.setToken(token);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setRole(faculty.getRole());
            response.setMessage("Faculty login successful");
        } catch (AuthenticationException e) {
            response.setStatusCode(401);
            response.setMessage("Login failed: " + e.getMessage());
        }
        return response;
    }

    // Refresh JWT token
    public RequestResponse refreshToken(RequestResponse request) {
        RequestResponse response = new RequestResponse();
        try {
            String email = jwtUtils.extractUsername(request.getToken());
            Faculty faculty = facultyRepository.findByEmail(email).orElseThrow();
            if (jwtUtils.isTokenValid(request.getToken(), faculty)) {
                String newToken = jwtUtils.generateToken(faculty);
                response.setToken(newToken);
                response.setRefreshToken(request.getToken());
                response.setExpirationTime("24Hr");
                response.setStatusCode(200);
                response.setMessage("Token refreshed successfully");
            } else {
                response.setStatusCode(403);
                response.setMessage("Invalid token");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error: " + e.getMessage());
        }
        return response;
    }

    // Get all faculties
    public RequestResponse getAllFaculties() {
        RequestResponse response = new RequestResponse();
        try {
            List<Faculty> faculties = facultyRepository.findAll();
            if (!faculties.isEmpty()) {
                response.setFacultyList(faculties);
                response.setStatusCode(200);
                response.setMessage("Faculties fetched successfully");
            } else {
                response.setStatusCode(404);
                response.setMessage("No faculties found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error: " + e.getMessage());
        }
        return response;
    }

    // Get faculty by ID
    public RequestResponse getFacultyById(Integer id) {
        RequestResponse response = new RequestResponse();
        try {
            Faculty faculty = facultyRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            response.setFaculty(faculty);
            response.setStatusCode(200);
            response.setMessage("Faculty found successfully");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error: " + e.getMessage());
        }
        return response;
    }

    // Update faculty
    public RequestResponse updateFaculty(Integer id, FacultyDTO dto) {
        RequestResponse response = new RequestResponse();
        try {
            Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
            if (optionalFaculty.isPresent()) {
                Faculty faculty = optionalFaculty.get();
                faculty.setName(dto.getFacultyName());
                faculty.setEmail(dto.getFacultyEmail());
                faculty.setPhone(dto.getFacultyPhone());
                faculty.setRole(dto.getFacultyRole());
                faculty.setDepartment(dto.getFacultyDepartment());
                faculty.setDesignation(dto.getFacultyDesignation());

                Faculty updated = facultyRepository.save(faculty);
                response.setFaculty(updated);
                response.setStatusCode(200);
                response.setMessage("Faculty updated successfully");
            } else {
                response.setStatusCode(404);
                response.setMessage("Faculty not found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Update failed: " + e.getMessage());
        }
        return response;
    }

    // Delete faculty
    public RequestResponse deleteFaculty(Integer id) {
        RequestResponse response = new RequestResponse();
        try {
            if (facultyRepository.existsById(id)) {
                facultyRepository.deleteById(id);
                response.setStatusCode(200);
                response.setMessage("Faculty deleted successfully");
            } else {
                response.setStatusCode(404);
                response.setMessage("Faculty not found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error while deleting: " + e.getMessage());
        }
        return response;
    }

    // Get faculty info by email
    public RequestResponse getFacultyInfo(String email) {
        RequestResponse response = new RequestResponse();
        try {
            Optional<Faculty> facultyOpt = facultyRepository.findByEmail(email);
            if (facultyOpt.isPresent()) {
                response.setFaculty(facultyOpt.get());
                response.setStatusCode(200);
                response.setMessage("Faculty info fetched");
            } else {
                response.setStatusCode(404);
                response.setMessage("Faculty not found");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error: " + e.getMessage());
        }
        return response;
    }
}
