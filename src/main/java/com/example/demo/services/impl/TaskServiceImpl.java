package com.example.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.services.TasksServices;

@Service("taskService")
public class TaskServiceImpl implements TasksServices{
	
	private final TaskRepository taskRepo;
	
	TaskServiceImpl(TaskRepository taskRepo){
		this.taskRepo = taskRepo;
	}
	
	@Autowired
	@Qualifier("taskRepository")

	@Override
	public List<Task> showAllTasks() {
		// TODO Auto-generated method stub
		return taskRepo.findAll();
	}

	@Override
	public Task addTask(Task task) {
		// TODO Auto-generated method stub
		return taskRepo.save(task);
	}

	@Override
	public int deleteTask(int id) {
		// TODO Auto-generated method stub
		taskRepo.deleteById(id);
		return 0;
	}

	@Override
	public Task updateTask(Task task) {
		// TODO Auto-generated method stub
		return taskRepo.save(task);
	}
	
	

}
