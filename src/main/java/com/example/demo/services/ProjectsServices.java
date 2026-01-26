package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Project;
import com.example.demo.model.ProjectDTO;

public interface ProjectsServices {

	List<ProjectDTO> showAllProjects();

	List<ProjectDTO> showMyProjects(String email);

	Project addProject(ProjectDTO projectDTO);

	int deleteProject(Long id);

	Project updateProject(Long id, ProjectDTO projectDTO);

}
