package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Task;
import com.example.demo.model.TaskDTO;

public interface TasksServices {

	List<TaskDTO> showAllTasks();

	Task addTask(TaskDTO taskDTO);

	int deleteTask(Long id);

	Task updateTask(Long id, TaskDTO taskDTO);

	boolean existTask(Long id);

}
