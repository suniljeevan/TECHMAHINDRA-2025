package com.cms.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.dto.LoginRequest;
import com.cms.dto.LoginResponse;
import com.cms.entity.CustomUser;
import com.cms.repository.UserRepository;
import com.nimbusds.jose.jwk.source.ImmutableSecret;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Value("${security.jwt.secret-key}")
	private String secretKey;
	@Value("${security.jwt.issuer}")
	private String issuer;
	
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	
	public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
	}

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest loginRequest) {
	    Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
	    
	    CustomUser user = userRepository.findByEmail(loginRequest.getEmail());
	    
	    // generate JWT token
	    Instant now = Instant.now();
	    JwtClaimsSet claims = JwtClaimsSet.builder()
	            .issuer(issuer)
	            .subject(user.getEmail())
	            .issuedAt(now)
	            .expiresAt(now.plusSeconds(24 * 3600))
	            .claim("authorities", List.of(user.getRole()))
	            .build();
	    JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
	    JwtEncoderParameters params = JwtEncoderParameters.from(header, claims);
	    NimbusJwtEncoder encoder = new NimbusJwtEncoder(
	            new ImmutableSecret<>(secretKey.getBytes()));
	    
	    String token = encoder.encode(params).getTokenValue();
	    
	    return new LoginResponse(token, user.getId(), user.getRole());
	}
}
