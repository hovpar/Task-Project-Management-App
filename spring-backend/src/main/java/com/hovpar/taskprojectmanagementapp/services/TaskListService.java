package com.hovpar.taskprojectmanagementapp.services;

import com.hovpar.taskprojectmanagementapp.entities.Project;
import com.hovpar.taskprojectmanagementapp.entities.TaskList;
import com.hovpar.taskprojectmanagementapp.repositories.ProjectRepository;
import com.hovpar.taskprojectmanagementapp.repositories.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;

    private final ProjectRepository projectRepository;

    @Autowired
    public TaskListService(TaskListRepository taskListRepository, ProjectRepository projectRepository) {
        this.taskListRepository = taskListRepository;
        this.projectRepository = projectRepository;
    }

    public List<TaskList> getTaskListsByProject(Long projectId) {
        return taskListRepository.findAllByProjectId(projectId);
    }

    public TaskList createTaskList(Long projectId, TaskList taskList ) {
        // Ensure project exists
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        if (taskListRepository.findByProjectIdAndName(projectId, taskList.getTitle()).isPresent()) {
            throw new IllegalArgumentException(
                    "TaskList with name '" + taskList.getTitle() + "' already exists in this project"
            );
        }
        taskList.setProject(project);
        return taskListRepository.save(taskList);
    }

    // Update existing list by ID
    public TaskList updateTaskList(Long taskListId, String newTitle) {
        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("TaskList not found with taskListId: " + taskListId));

        // Check for duplicate title only within the same project
        Long projectId = taskList.getProject().getId();
        if (taskListRepository.findByProjectIdAndTitle(projectId, newTitle).isPresent()) {
            throw new IllegalArgumentException("TaskList with name '" + newTitle + "' already exists in this project");
        }

        taskList.setTitle(newTitle);
        return taskListRepository.save(taskList);
    }

    // Delete by ID
    public void deleteTaskList(Long id) {
        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TaskList not found with id: " + id));
        taskListRepository.delete(taskList);
    }

}
