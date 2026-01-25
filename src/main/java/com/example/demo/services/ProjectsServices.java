package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Project;

public interface ProjectsServices {

	List<Project> showAllProjects();

	List<Project> showMyProjects(String email);

	Project addProject(Project project);

	int deleteProject(Long id);

	Project updateProject(Project project);

}
