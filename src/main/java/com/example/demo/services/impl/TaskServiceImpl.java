package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import com.example.demo.model.TaskAdminDTO;
import com.example.demo.model.TaskDTO;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.services.TasksServices;

@Service("taskService")
public class TaskServiceImpl implements TasksServices {

	@Autowired
	@Qualifier("taskRepository")
	private TaskRepository taskRepository;
	
	@Autowired
	@Qualifier("projectRepository")
	private ProjectRepository projectRepository;

	// Method to transform TaskDTO
	private TaskDTO transformTaskDTO(Task task) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(task, TaskDTO.class);
	}

	// Method to transform Task
	private Task transformTask(TaskDTO taskDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(taskDTO, Task.class);
	}

	@Override
	public List<TaskDTO> showAllTasks() {
		List<TaskDTO> taskDTOs = new java.util.ArrayList<>();
		for (Task task : taskRepository.findAll()) {
			taskDTOs.add(transformTaskDTO(task));
		}
		return taskDTOs;
	}

	@Override
	public Task addTask(TaskDTO taskDTO) {
		return taskRepository.save(transformTask(taskDTO));
	}

	@Override
	public int deleteTask(Long id) {
		if (!taskRepository.existsById(id)) {
			throw new IllegalArgumentException("The task not exist for delete");
		}
		taskRepository.deleteById(id);
		return 0;
	}

	@Override
	public Task updateTask(Long id, TaskDTO taskDTO) {
		if (!taskRepository.existsById(id)) {
			throw new IllegalArgumentException("The task not exist for update");
		}
		return taskRepository.save(transformTask(taskDTO));
	}

	@Override
	public boolean existTask(Long id) {
		return taskRepository.existsById(id);
	}

	@Override
	public List<TaskAdminDTO> showAllTasksByProject(Long projectId) {
		Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
		
		List<Task> tasks = project.getTasks();
		List<TaskAdminDTO> tasksTransformed = new ArrayList<>();
		
		for(Task t: tasks) {
			TaskAdminDTO dto = new TaskAdminDTO(t.getName(), t.getDescription());
			tasksTransformed.add(dto);
		}
		
		return tasksTransformed;
	}

}
