package com.org.TMS.service;

import com.org.TMS.model.User;
import com.org.TMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password before saving
        userRepository.save(user);
    }
}
