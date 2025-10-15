package com.hovpar.taskprojectmanagementapp.services;

import com.hovpar.taskprojectmanagementapp.models.Task;
import com.hovpar.taskprojectmanagementapp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findTasksByTaskListId(Long taskListId, Sort sort) {
        return taskRepository.findTasksByTaskListId(taskListId, sort);
    }

}

