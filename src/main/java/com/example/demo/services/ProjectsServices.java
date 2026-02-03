package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Project;
import com.example.demo.model.ProjectCreateDTO;

public interface ProjectsServices {

	List<ProjectCreateDTO> showAllProjects();

	List<ProjectCreateDTO> showMyProjects(String email);

	Project addProject(ProjectCreateDTO projectDTO);

	int deleteProject(Long id);

	Project updateProject(Long id, ProjectCreateDTO projectDTO);

	boolean existProject(Long id);

}
