package com.hovpar.taskprojectmanagementapp.services;

import com.hovpar.taskprojectmanagementapp.entities.Task;
import com.hovpar.taskprojectmanagementapp.entities.TaskList;
import com.hovpar.taskprojectmanagementapp.repositories.TaskListRepository;
import com.hovpar.taskprojectmanagementapp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }


    public List<Task> getTasksByTaskListId(Long taskListId) {
        return taskRepository.findTasksByTaskListId(taskListId);
    }

    public Task createTask(Long taskListId, Task taskDetails) {
        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(()-> new IllegalArgumentException("Task list id " + taskListId + " not found"));
        taskDetails.setTaskList(taskList);
        return taskRepository.save(taskDetails);
    }
    // Update existing list by ID
    public Task updateTask(Long taskId, Task taskDetails) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + taskId));

        // Update only the fields you want to allow changing
        existingTask.setTitle(taskDetails.getTitle());
        existingTask.setDescription(taskDetails.getDescription());
        existingTask.setAddedAt(taskDetails.getAddedAt());

        // Allow moving to a different task list
        if (taskDetails.getTaskList() != null) {
            existingTask.setTaskList(taskDetails.getTaskList());
        }

        return taskRepository.save(existingTask);
    }

    // Delete by ID
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + taskId));
        taskRepository.delete(task);
    }

}

