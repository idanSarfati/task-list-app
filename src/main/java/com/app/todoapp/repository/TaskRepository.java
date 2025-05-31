package com.app.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.todoapp.models.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompletedTrue();
    List<Task> findByCompletedFalse();
    List<Task> findByPriority(String priority);
    List<Task> findByCompletedAndPriority(boolean completed, String priority);
}
