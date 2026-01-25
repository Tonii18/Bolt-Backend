package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Task;

public interface TasksServices {

	List<Task> showAllTasks();

	Task addTask(Task task);

	int deleteTask(Long id);

	Task updateTask(Task task);

}
