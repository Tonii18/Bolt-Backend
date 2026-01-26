package com.example.demo.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
    public int deleteUser(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("The user not exist to delete");
        }
        userRepository.deleteById(id);
        return 0;
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        if (!userRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("The user not exist to update");
        }
        return userRepository.save(transformUser(userDTO));
    }

    @Override
    public boolean existUser(Long id) {
        return userRepository.findById(id).isPresent();
    }

}
