package com.example.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Project;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.services.ProjectsServices;

@Service("projectService")
public class ProjectServiceImpl implements ProjectsServices{
	
	private final ProjectRepository projectRepo;
	
	ProjectServiceImpl(ProjectRepository projectRepo){
		this.projectRepo = projectRepo;
	}
	
	@Autowired
	@Qualifier("projectRepository")

	@Override
	public List<Project> showAllProjects() {
		// TODO Auto-generated method stub
		return projectRepo.findAll();
	}

	@Override
	public Project addProject(Project project) {
		// TODO Auto-generated method stub
		return projectRepo.save(project);
	}

	@Override
	public int deleteProject(int id) {
		// TODO Auto-generated method stub
		projectRepo.deleteById(id);
		return 0;
	}

	@Override
	public Project updateProject(Project project) {
		// TODO Auto-generated method stub
		return projectRepo.save(project);
	}
	
	

}
