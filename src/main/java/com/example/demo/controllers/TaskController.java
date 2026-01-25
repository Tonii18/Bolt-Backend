package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Task;
import com.example.demo.services.TasksServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TasksServices tasksServices;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return tasksServices.addTask(task);
    }

    @GetMapping
    public List<Task> getMethodName() {
        return tasksServices.showAllTasks();
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return tasksServices.updateTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        tasksServices.deleteTask(id);
    }
}
