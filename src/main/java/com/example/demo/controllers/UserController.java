package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.model.UserDTO;
import com.example.demo.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/allUsers")
	public ResponseEntity<List<UserDTO>> getAllUser() {
		List<UserDTO> users = userService.showAllUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/currentUser")
	public ResponseEntity<User> getCurrentUser() {
		User currentUser = userService.getCurrentUser();
		return ResponseEntity.ok(currentUser);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		if (!userService.existUser(id)) {
			return ResponseEntity.notFound().build();
		}

		User userUpdate = userService.updateUser(id, userDTO);
		return ResponseEntity.ok(userUpdate);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		if (userService.deleteUser(id) == 0) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
