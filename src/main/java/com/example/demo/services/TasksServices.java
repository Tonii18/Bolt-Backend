package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Task;
import com.example.demo.model.TaskAdminDTO;
import com.example.demo.model.TaskDTO;
import com.example.demo.model.TaskEditDTO;

public interface TasksServices {

	List<TaskDTO> showAllTasks();

	Task addTask(TaskDTO taskDTO);

	int deleteTask(Long id);

	Task updateTask(Long id, TaskEditDTO taskEditDTO);

	boolean existTask(Long id);
	
	List<TaskAdminDTO> showAllTasksByProject(Long projectId);

}
