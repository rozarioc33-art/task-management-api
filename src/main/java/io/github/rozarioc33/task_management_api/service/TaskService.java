package io.github.rozarioc33.task_management_api.service;

import io.github.rozarioc33.task_management_api.entity.Task;
import io.github.rozarioc33.task_management_api.exception.TaskNotFoundException;
import io.github.rozarioc33.task_management_api.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {

        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {

        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("Task not found with id: " + taskId);
        }

        return optionalTask.get();
    }

    public Task updateTask(Task task, Long taskId) {

        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("Task not found with id: " + taskId);
        }

        Task existingTask = optionalTask.get();

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setCompleted(task.isCompleted());

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long taskId) {

        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("Task not found with id: " + taskId);
        }

        Task existingTask = optionalTask.get();

        taskRepository.delete(existingTask);
    }
}
