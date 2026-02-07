package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Project;
import com.example.demo.model.ProjectCreateDTO;
import com.example.demo.model.ProjectDTO;
import com.example.demo.model.ProjectEditDTO;
import com.example.demo.model.UserDTO;
import com.example.demo.model.UserProjectDTO;
import com.example.demo.services.ProjectsServices;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ResponseEntity<Project> createProject(@RequestBody ProjectCreateDTO projectDTO) {
        Project newProject = projectsService.addProject(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProject);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProjectEditDTO> updateProject(@PathVariable Long id, @RequestBody ProjectEditDTO projectDTO) {
        if (!projectsService.existProject(id)) {
            return ResponseEntity.notFound().build();
        }

        ProjectEditDTO projectUpdate = projectsService.updateProject(id, projectDTO);
        return ResponseEntity.ok(projectUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (projectsService.deleteProject(id) == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * Controllers related to users and projects
     */

    // Get users by project

    @GetMapping("/{projectId}/users")
    public ResponseEntity<List<UserProjectDTO>> getProjectUsers(@PathVariable Long projectId) {
        List<UserProjectDTO> users = projectsService.getUsersByProject(projectId).stream()
                .map(user -> new UserProjectDTO(
                        user.getId(),
                        user.getFullName(),
                        user.getEmail()))
                .toList();

        return ResponseEntity.ok(users);
    }

    // Add user to project

    @PostMapping("/{projectId}/users/{userId}")
    public ResponseEntity<Void> addUserToProject(@PathVariable Long projectId, @PathVariable Long userId) {
        projectsService.addUserToProject(projectId, userId);

        return ResponseEntity.ok().build();
    }

    // Delete user from project

    @DeleteMapping("/{projectId}/users/{userId}")
    public ResponseEntity<Void> removeUserFromProject(@PathVariable Long projectId, @PathVariable Long userId) {
        projectsService.removeUserfromProject(projectId, userId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/projectsByUser/{userId}")
    public ResponseEntity<List<Project>> getProjectsByUser(@PathVariable Long userId) {
        List<Project> projects = projectsService.getProjectsByUser(userId);
        return ResponseEntity.ok(projects);
    }

}
