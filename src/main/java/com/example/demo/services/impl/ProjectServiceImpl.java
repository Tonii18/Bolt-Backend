package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.model.ProjectDTO;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.ProjectsServices;
import org.modelmapper.ModelMapper;

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
	private Project transformProject(ProjectDTO projectDTO) {
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
	public Project addProject(ProjectDTO projectDTO) {
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
	public Project updateProject(Long id, ProjectDTO projectDTO) {
		if (!projectRepository.existsById(id)) {
			throw new IllegalArgumentException("The project not exist for edit");
		}
		return projectRepository.save(transformProject(projectDTO));
	}

}
