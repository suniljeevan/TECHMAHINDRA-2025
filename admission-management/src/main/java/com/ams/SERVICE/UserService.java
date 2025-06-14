package com.ams.SERVICE;

import com.ams.MODEL.User;
import com.ams.REPOSITORY.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

 // Save user with encoded password
    public void saveUser(User user) {
        User userToSave = new User();
        userToSave.setEmail(user.getEmail());          // Use email as login identifier
        userToSave.setPassword(passwordEncoder.encode(user.getPassword()));  // Encode password
        userToSave.setRole(user.getRole());
        userRepository.save(userToSave);
    }

    // Check if a user already exists by email
    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // Find user by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
