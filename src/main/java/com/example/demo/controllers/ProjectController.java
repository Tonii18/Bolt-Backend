package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Project;
import com.example.demo.model.ProjectDTO;
import com.example.demo.services.ProjectsServices;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectsServices projectsService;

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return projectsService.showAllProjects();
    }

    @GetMapping()
    public List<ProjectDTO> getMyProjects(Authentication authentication) {
        return projectsService.showMyProjects(authentication.getName());
    }

    @PostMapping
    public Project createProject(@RequestBody ProjectDTO projectDTO) {
        return projectsService.addProject(projectDTO);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        return projectsService.updateProject(id, projectDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectsService.deleteProject(id);
    }

}
