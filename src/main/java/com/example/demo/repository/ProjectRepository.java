package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Serializable> {
    List<Project> findByUsersId(Long userId);
}
