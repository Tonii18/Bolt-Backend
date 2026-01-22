package com.example.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserDTO;
import com.example.demo.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> request){
		String token = authService.login(request.get("email"), request.get("password"));
		return ResponseEntity.ok(Map.of("token", token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
		authService.register(userDTO);
		return ResponseEntity.ok("User registered succesfully");
	}

}
