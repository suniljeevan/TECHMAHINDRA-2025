package com.cms.controller;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.dto.LoginDto;
import com.cms.dto.RegisterDto;
import com.cms.entity.CustomUser;
import com.cms.repository.UserRepository;
import com.nimbusds.jose.jwk.source.ImmutableSecret;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    @Value("${security.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${security.jwt.issuer}")
    private String issuer;
    
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterDto registerDto, BindingResult result) {
    	if(result.hasErrors()) {
    		return validateErrors(result);
    	}
    	
    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    	CustomUser user = new CustomUser();
    	user.setId(registerDto.getId());
    	user.setFirstName(registerDto.getFirstName());
    	user.setLastName(registerDto.getLastName());
    	user.setEmail(registerDto.getEmail());
    	user.setPhone(registerDto.getPhone());
    	user.setRole("STUDENT");
    	user.setCreateDate(new Date());
    	user.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
    	
    	try {
    		CustomUser checkUser = userRepository.findByEmail(registerDto.getEmail());
    		if(checkUser != null) {
    			return ResponseEntity.badRequest().body("Email address already registered!");
    		}
    		userRepository.save(user);
    		String jwtToken = createJwtToken(user);
    		
    		HashMap<String, Object> response = new HashMap<String, Object>();
    		response.put("token", jwtToken);
    		response.put("user", user);
    		response.put("role", user.getRole());
    		return ResponseEntity.ok(response);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	HashMap<String, String> errorResponse = new HashMap<>();
    	errorResponse.put("error", "Error occurred during registration");
    	return ResponseEntity.badRequest().body(errorResponse);
    }

    private ResponseEntity<Object> validateErrors(BindingResult result) {
    	List<ObjectError> errorsList = result.getAllErrors();
		HashMap<String, String> errorsMap = new HashMap<String, String>();
		for(int i = 0; i < errorsList.size(); i++) {
			FieldError error = (FieldError) errorsList.get(i);
			errorsMap.put(error.getField(), error.getDefaultMessage());
		}
		return ResponseEntity.badRequest().body(errorsMap);
		
	}

	private String createJwtToken(CustomUser user) throws Exception {
        Instant now = Instant.now();
        
        JwtClaimsSet claims = JwtClaimsSet.builder()
        	    .issuer(issuer)
        	    .subject(user.getEmail())
        	    .issuedAt(now)
        	    .expiresAt(now.plusSeconds(24 * 3600)) // 24 hours expiration
        	    .claim("authorities", List.of(user.getRole()))
        	    .build();
        
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        JwtEncoderParameters params = JwtEncoderParameters.from(header, claims);
        NimbusJwtEncoder encoder = new NimbusJwtEncoder(
        		new ImmutableSecret<>(jwtSecretKey.getBytes()));
        
        return encoder.encode(params).getTokenValue();
    }
    
    // for login
    
    @Autowired
    private AuthenticationManager autherAuthenticationManager;
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto, BindingResult result){
    	if(result.hasErrors()) {
    		return validateErrors(result);
    	}
    	try {
    		autherAuthenticationManager.authenticate(
    				new UsernamePasswordAuthenticationToken(
    						loginDto.getEmail(), 
    						loginDto.getPassword()
    						));
    		CustomUser user = userRepository.findByEmail(loginDto.getEmail());
    		String jwtToken = createJwtToken(user);
    		HashMap<String, Object> response = new HashMap<String, Object>();
    		response.put("token", jwtToken);
    		response.put("user", user);
    		
    		return ResponseEntity.ok(response);
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    	return ResponseEntity.badRequest().body("Bad username or password");
    }
    
    // authenticate users based on JWT
    @GetMapping("/profile")
    public ResponseEntity<Object> profile(Authentication authentication) {
    	HashMap<String, Object> response = new HashMap<String, Object>();
    	response.put("email", authentication.getName());
    	
    	CustomUser user = userRepository.findByEmail(authentication.getName());
    	response.put("Authorities", authentication.getAuthorities());
    	
    	response.put("User", user);
    	
    	return ResponseEntity.ok(response);
    }
    
}
