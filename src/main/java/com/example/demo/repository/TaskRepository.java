package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Task;

@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<Task, Serializable> {
    List<Task> findByUserId(Long userId);

    List<Task> findByProjectId(Long projectId);
}
