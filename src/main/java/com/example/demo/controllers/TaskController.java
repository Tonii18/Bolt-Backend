package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Task;
import com.example.demo.model.TaskAdminDTO;
import com.example.demo.model.TaskDTO;
import com.example.demo.services.TasksServices;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TasksServices tasksServices;

    @PostMapping("/createdTask")
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        Task newTask = tasksServices.addTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @GetMapping("/allTasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = tasksServices.showAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        if (!tasksServices.existTask(id)) {
            return ResponseEntity.notFound().build();
        }

        Task taskUpdate = tasksServices.updateTask(id, taskDTO);
        return ResponseEntity.ok(taskUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (tasksServices.deleteTask(id) == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("{projectId}/allTasks")
    public ResponseEntity<List<TaskAdminDTO>> getAllTasksByProject(@PathVariable Long projectId){
    	List<TaskAdminDTO> tasks = tasksServices.showAllTasksByProject(projectId);
    	
    	return ResponseEntity.ok(tasks);
    }
}
