// package com.example.techways.Security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;

// import com.example.techways.Models.User;
// import com.example.techways.Repository.UserRepository;

// import jakarta.transaction.Transactional;

// public class UserDetailsServiceImpl implements UserDetailsService {

//     @Autowired
//     UserRepository userRepository;

//     @Override
//     @Transactional
//     public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = userRepository.findByUsername(username)
//                 .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//         return UserDetailsImpl.build(user);
//     }

// }
