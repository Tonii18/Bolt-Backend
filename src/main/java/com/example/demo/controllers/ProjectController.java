package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Project;
import com.example.demo.services.ProjectsServices;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectsServices projectsService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectsService.showAllProjects();
    }

    @GetMapping
    public List<Project> getMyProjects(Authentication authentication) {
        return projectsService.showMyProjects(authentication.getName());
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectsService.addProject(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
        return projectsService.updateProject(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectsService.deleteProject(id);
    }

}
