package com.example.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.services.TasksServices;

@Service("taskService")
public class TaskServiceImpl implements TasksServices {

	@Autowired
	@Qualifier("taskRepository")
	TaskRepository taskRepository;

	@Override
	public List<Task> showAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task addTask(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public int deleteTask(Long id) {
		taskRepository.deleteById(id);
		return 0;
	}

	@Override
	public Task updateTask(Task task) {
		return taskRepository.save(task);
	}

}
