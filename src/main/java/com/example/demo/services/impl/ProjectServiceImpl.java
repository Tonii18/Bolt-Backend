package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.model.ProjectCreateDTO;
import com.example.demo.model.ProjectDTO;
import com.example.demo.model.ProjectEditDTO;
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

	// Method to transform ProjectDTO
	private ProjectDTO transformProjectDTO(Project project) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(project, ProjectDTO.class);
	}

	// Method to transform Project
	private Project transformProject(ProjectCreateDTO projectDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(projectDTO, Project.class);
	}

	@Override
	public List<ProjectDTO> showAllProjects() {
		List<ProjectDTO> projectDTOs = new ArrayList<>();
		for (Project project : projectRepository.findAll()) {
			projectDTOs.add(transformProjectDTO(project));
		}
		return projectDTOs;
	}

	@Override
	public List<ProjectDTO> showMyProjects(String email) {
		User user = userRepository.findByEmail(email).orElse(null);
		List<ProjectDTO> projectDTOs = new ArrayList<>();
		for (Project project : projectRepository.findByUsersId(user.getId())) {
			projectDTOs.add(transformProjectDTO(project));
		}
		return projectDTOs;
	}

	@Override
	public Project addProject(ProjectCreateDTO projectDTO) {
		return projectRepository.save(transformProject(projectDTO));
	}

	@Override
	public int deleteProject(Long id) {
		if (!projectRepository.existsById(id)) {
			throw new IllegalArgumentException("The project not exist for delete");
		}
		projectRepository.deleteById(id);
		return 0;
	}

	@Override
	public ProjectEditDTO updateProject(Long id, ProjectEditDTO projectDTO) {
		Project existingProject = projectRepository.findById(id).orElse(null);
		if (existingProject == null) {
			throw new IllegalArgumentException("The project not exist for update");
		}

		existingProject.setName(projectDTO.getName());
		existingProject.setDescription(projectDTO.getDescription());

		Project saved = projectRepository.save(existingProject);

		return new ProjectEditDTO(saved.getId(), saved.getName(), saved.getDescription());
	}

	@Override
	public boolean existProject(Long id) {
		return projectRepository.existsById(id);
	}

	@Override
	public void addUserToProject(Long projectId, Long userId) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new RuntimeException("Project not found"));
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		if (!project.getUsers().contains(user)) {
			project.getUsers().add(user);
			projectRepository.save(project);
		}
	}

	@Override
	public void removeUserfromProject(Long projectId, Long userId) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new RuntimeException("Project not found"));

		project.getUsers().removeIf(u -> u.getId().equals(userId));
		projectRepository.save(project);
	}

	@Override
	public List<User> getUsersByProject(Long projectId) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new RuntimeException("Project not found"));

		return project.getUsers();
	}

	@Override
	public List<ProjectDTO> getProjectsByUser(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		List<Project> projects = projectRepository.findByUsersId(user.getId());
		List<ProjectDTO> projectDTOs = new ArrayList<>();
		for (Project project : projects) {
			projectDTOs.add(transformProjectDTO(project));
		}
		return projectDTOs;
	}

	@Override
	public Long getProjectId(String name) {
		Project project = projectRepository.findByName(name);
		Long projectId = project.getId();
		
		return projectId;
	}

}
