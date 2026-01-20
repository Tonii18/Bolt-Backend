package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Project;
import com.example.demo.entity.Task;

public interface TasksServices {
	
	List<Task> showAllTasks();
	Task addTask(Task task);
	int deleteTask(int id);
	Task updateTask(Task task);

}
