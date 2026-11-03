package io.github.rozarioc33.task_management_api.controller;

import io.github.rozarioc33.task_management_api.entity.Task;
import io.github.rozarioc33.task_management_api.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PutMapping("/{id}")
    public Task updateTask(@RequestBody Task task, @PathVariable("id") Long taskId) {
        return taskService.updateTask(task, taskId);
    }
}
