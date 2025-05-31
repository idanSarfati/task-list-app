package com.app.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import com.app.todoapp.models.Task;

import com.app.todoapp.services.TaskService;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTasks(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "priority", required = false) String priority,
            Model model) {

        List<Task> tasks = taskService.getTasksByStatusAndPriority(status, priority);

        model.addAttribute("tasks", tasks);
        model.addAttribute("status", status);
        model.addAttribute("priority", priority);
        return "tasks";
    }



    @PostMapping
    public String createTasks(@RequestParam String title,
                            @RequestParam String priority) {
        taskService.createTask(title, priority);
        return "redirect:/";
    }


    @GetMapping("/{id}/delete")
    public String deleteTasks(@PathVariable Long id) {
        taskService.deleteTasks(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTasks(@PathVariable Long id) {
        taskService.toggleTasks(id);
        return "redirect:/";
    }

}
