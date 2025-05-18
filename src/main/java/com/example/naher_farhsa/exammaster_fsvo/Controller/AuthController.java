package com.example.naher_farhsa.exammaster_fsvo.Controller;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.example.naher_farhsa.exammaster_fsvo.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.ui.Model;

import com.example.naher_farhsa.exammaster_fsvo.Entity.User;
import com.example.naher_farhsa.exammaster_fsvo.Service.JwtService;
import com.example.naher_farhsa.exammaster_fsvo.config.PasswordEncoderConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Collection;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService ,PasswordEncoder passwordEncoder,UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder=passwordEncoder;
        this.userRepository=userRepository;
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes
    ) {
        System.out.println(username+"      "+password+" Entered here ");



        try {
            // 1. Authenticate user
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // 2. Generate JWT Token
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtService.generateToken(userDetails);

            System.out.println("token is   "+token);

            // 3. Set JWT as HttpOnly cookie
            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true); // Only for HTTPS
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60); // 1 day
            response.addCookie(cookie);

            System.out.println("correct password entered ");

            /*// 4. Check role of user and redirect to /exammaster/dashboard and /exammaster/student-dashboard
            return "redirect:/exammaster/dashboard";*/

            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

            for (GrantedAuthority authority : authorities) {
                String role = authority.getAuthority();
                if (role.equals("ROLE_ADMIN")) {
                    return "redirect:/exammaster/dashboard";
                } else if (role.equals("ROLE_USER")) {
                    return "redirect:/exammaster/studentDashboard";
                }
            }

            // Default redirect if role not matched
            return "redirect:/exammaster/login";

        } catch (AuthenticationException e) {
            // If login fails, redirect back with error message
            System.out.println("wrong password entered ");
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/exammaster/login";
        }
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            // Add user registration logic here, e.g., password encoding, saving to DB
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "redirect:/exammaster/login";
        } catch (Exception e) {
            model.addAttribute("error", "Username already exists or error occurred.");
            return "signup";
        }
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());  // prepare empty User object for the form
        return "signup";  // return the view name for the signup page (e.g., signup.html or signup.jsp)
    }

}

