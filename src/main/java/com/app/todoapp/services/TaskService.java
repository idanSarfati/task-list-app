package com.app.todoapp.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.app.todoapp.models.Task;
import com.app.todoapp.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void createTask(String title, String priority) {
        Task task = new Task();
        task.setTitle(title);
        task.setPriority(priority);
        task.setCompleted(false);
        task.setCreatedAt(LocalDateTime.now());
        taskRepository.save(task);
    }


    public void deleteTasks(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleTasks(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }

    public List<Task> getTasksByStatusAndPriority(String status, String priority) {
        if ((status == null || status.equals("all")) && (priority == null || priority.equals("all"))) {
            return taskRepository.findAll();
        }

        if ((status == null || status.equals("all"))) {
            return taskRepository.findByPriority(priority);
        }

        if ((priority == null || priority.equals("all"))) {
            return status.equals("completed")
                    ? taskRepository.findByCompletedTrue()
                    : taskRepository.findByCompletedFalse();
        }

        // Filter by both
        boolean isCompleted = status.equals("completed");
        return taskRepository.findByCompletedAndPriority(isCompleted, priority);
    }


}


