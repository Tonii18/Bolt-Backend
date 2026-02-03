package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Project;
import com.example.demo.model.ProjectCreateDTO;
import com.example.demo.model.ProjectDTO;

public interface ProjectsServices {

	List<ProjectDTO> showAllProjects();

	List<ProjectDTO> showMyProjects(String email);

	Project addProject(ProjectCreateDTO projectDTO);

	int deleteProject(Long id);

	Project updateProject(Long id, ProjectCreateDTO projectDTO);

	boolean existProject(Long id);

}
