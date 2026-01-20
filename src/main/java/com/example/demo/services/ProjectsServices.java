package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Project;

public interface ProjectsServices {
	
	List<Project> showAllProjects();
	Project addProject(Project project);
	int deleteProject(int id);
	Project updateProject(Project project);

}
