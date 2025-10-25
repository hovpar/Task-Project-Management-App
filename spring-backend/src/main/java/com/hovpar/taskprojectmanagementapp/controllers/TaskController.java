package com.hovpar.taskprojectmanagementapp.controllers;

import com.hovpar.taskprojectmanagementapp.entities.Task;
import com.hovpar.taskprojectmanagementapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-lists/{taskListId}/tasks")
public class TaskController {

    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasksByTaskListId(@PathVariable Long taskListId) {
       List<Task> tasks = taskService.getTasksByTaskListId(taskListId);
       return ResponseEntity.ok(tasks);

    }
    @PostMapping
    public ResponseEntity<Task> createTask(
            @PathVariable Long taskListId,
            @RequestBody Task task) {
        Task created = taskService.createTask(taskListId, task);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Update an existing task
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long taskId,
            @RequestBody Task taskDetails) {
        Task updated = taskService.updateTask(taskId, taskDetails);
        return ResponseEntity.ok(updated);
    }

    // Delete a task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
