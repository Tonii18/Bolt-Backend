package com.example.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.ProjectsServices;

@Service("projectService")
public class ProjectServiceImpl implements ProjectsServices {

	@Autowired
	@Qualifier("projectRepository")
	private ProjectRepository projectRepository;

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Override
	public List<Project> showAllProjects() {
		return projectRepository.findAll();
	}

	@Override
	public List<Project> showMyProjects(String email) {
		User user = userRepository.findByEmail(email).orElse(null);
		return projectRepository.findByUsersId(user.getId());
	}

	@Override
	public Project addProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public int deleteProject(Long id) {
		projectRepository.deleteById(id);
		return 0;
	}

	@Override
	public Project updateProject(Project project) {
		return projectRepository.save(project);
	}

}
