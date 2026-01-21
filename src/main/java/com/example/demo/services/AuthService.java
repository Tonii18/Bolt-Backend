package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	public String login(String email, String password) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return tokenProvider.generateToken(authentication);
	}
	
	public User register(User user) {
		if(userRepo.findByEmail(user.getEmail()).isPresent()) {
			throw new RuntimeException("Email is already taken!");
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRol("ROLE_USER");
		
		return userRepo.save(user);
	}

}
