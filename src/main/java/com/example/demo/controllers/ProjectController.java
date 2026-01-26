package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/allProjects")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projects = projectsService.showAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/userProjects")
    public ResponseEntity<List<ProjectDTO>> getMyProjects(Authentication authentication) {
        List<ProjectDTO> myProjects = projectsService.showMyProjects(authentication.getName());
        return ResponseEntity.ok(myProjects);
    }

    @PostMapping("/createdProject")
    public ResponseEntity<Project> createProject(@RequestBody ProjectDTO projectDTO) {
        Project newProject = projectsService.addProject(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        if (!projectsService.existProject(id)) {
            return ResponseEntity.notFound().build();
        }

        Project projectUpdate = projectsService.updateProject(id, projectDTO);
        return ResponseEntity.ok(projectUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (projectsService.deleteProject(id) == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
