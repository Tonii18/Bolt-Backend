package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.model.UserDTO;

public interface UserService {

    List<UserDTO> showAllUsers();

    int deleteUser(Long id);

    User updateUser(Long id, UserDTO userDTO);
}
