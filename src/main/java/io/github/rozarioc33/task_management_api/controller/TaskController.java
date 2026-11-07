package io.github.rozarioc33.task_management_api.controller;

import io.github.rozarioc33.task_management_api.entity.Task;
import io.github.rozarioc33.task_management_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(
            @Valid @RequestBody Task task
    ) {

        Task savedTask = taskService.createTask(task);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {

        List<Task> tasks = taskService.getAllTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long taskId) {

        try {
            Task task = taskService.getTaskById(taskId);
            return ResponseEntity.ok(task);

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @Valid @RequestBody Task task,
            @PathVariable("id") Long taskId) {

        try {
            Task updatedTask = taskService.updateTask(task, taskId);

            return ResponseEntity.ok(updatedTask);

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long taskId) {

        try {
            taskService.deleteTask(taskId);

            return ResponseEntity.noContent().build();

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
