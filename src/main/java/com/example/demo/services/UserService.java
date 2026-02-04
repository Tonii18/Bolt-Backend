package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.model.UserDTO;
import com.example.demo.model.UserProjectDTO;

public interface UserService {

	List<UserProjectDTO> showAllUsers();

	User getCurrentUser();

	int deleteUser(Long id);

	User updateUser(Long id, UserDTO userDTO);

	boolean existUser(Long id);
}
