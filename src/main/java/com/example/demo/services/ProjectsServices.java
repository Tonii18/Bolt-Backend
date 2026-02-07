package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.model.ProjectCreateDTO;
import com.example.demo.model.ProjectDTO;
import com.example.demo.model.ProjectEditDTO;

public interface ProjectsServices {

	List<ProjectDTO> showAllProjects();

	List<ProjectDTO> showMyProjects(String email);

	Project addProject(ProjectCreateDTO projectDTO);

	int deleteProject(Long id);

	ProjectEditDTO updateProject(Long id, ProjectEditDTO projectDTO);

	boolean existProject(Long id);
	
	void addUserToProject(Long projectId, Long userId);
	
	void removeUserfromProject(Long projectId, Long userId);
	
	List<User> getUsersByProject(Long projectId);

	List<Project> getProjectsByUser(Long userId);

}
