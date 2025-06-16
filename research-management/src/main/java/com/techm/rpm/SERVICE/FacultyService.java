package com.techm.rpm.SERVICE;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.rpm.MODEL.Faculty;
import com.techm.rpm.REPOSITORY.FacultyRepository;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    // Method to authenticate a faculty using email and password
    public Faculty authenticateFaculty(String email, String password) {
        Optional<Faculty> facultyOptional = facultyRepository.findByFacultyEmailAndFacultyPassword(email, password);
        if (facultyOptional.isPresent()) {
            return facultyOptional.get(); // Return the faculty if found
        } else {
            // Return null or throw a custom exception (for failed authentication)
            return null;
        }
    }
}
