package io.github.rozarioc33.task_management_api.controller;

import io.github.rozarioc33.task_management_api.entity.Task;
import io.github.rozarioc33.task_management_api.exception.TaskNotFoundException;
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
    public ResponseEntity<?> getTaskById(@PathVariable("id") Long taskId) {

        try {
            Task task = taskService.getTaskById(taskId);
            return ResponseEntity.ok(task);

        } catch (TaskNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(
            @Valid @RequestBody Task task,
            @PathVariable("id") Long taskId) {

        try {
            Task updatedTask = taskService.updateTask(task, taskId);

            return ResponseEntity.ok(updatedTask);

        } catch (TaskNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long taskId) {

        try {
            taskService.deleteTask(taskId);

            return ResponseEntity.noContent().build();

        } catch (TaskNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
