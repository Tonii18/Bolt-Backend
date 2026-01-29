package com.example.demo.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.SecurityConfig;
import com.example.demo.entity.User;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    // Method to transform UserDTO
    private UserDTO transformUserDTO(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }

    // Method to transform User
    private User transformUser(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public List<UserDTO> showAllUsers() {
        List<UserDTO> userDTOs = new java.util.ArrayList<>();
        for (User user : userRepository.findAll()) {
            userDTOs.add(transformUserDTO(user));
        }
        return userDTOs;

    }
    
    @Override
	public User getCurrentUser() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	if(authentication == null || !authentication.isAuthenticated()) {
    		throw new IllegalStateException("There is no authenticated user");
    	}
    	
    	String email = authentication.getName();
    	
    	User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    	
		return user;
	}

    @Override
    public int deleteUser(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("The user not exist to delete");
        }
        userRepository.deleteById(id);
        return 0;
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
    	User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The user not exist to update"));

        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setRole("ROLE_USER");

        if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
            user.setPassword(SecurityConfig.passwordEncoder().encode(userDTO.getPassword()));
        }

        return userRepository.save(user);
    }

    @Override
    public boolean existUser(Long id) {
        return userRepository.findById(id).isPresent();
    }

}
