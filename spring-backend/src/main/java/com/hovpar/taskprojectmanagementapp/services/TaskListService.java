package com.hovpar.taskprojectmanagementapp.services;

import com.hovpar.taskprojectmanagementapp.models.Project;
import com.hovpar.taskprojectmanagementapp.models.TaskList;
import com.hovpar.taskprojectmanagementapp.repositories.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;

    @Autowired
    public TaskListService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    public List<TaskList> getTaskListsByProject(Long projectId) {
        return taskListRepository.findAllByProjectId(projectId);
    }

    public TaskList createTaskList(TaskList taskList, Project project) {

        if (taskListRepository.findByProjectIdAndName(project.getId(), taskList.getTitle()).isPresent()) {
            throw new IllegalArgumentException(
                    "TaskList with name '" + taskList.getTitle() + "' already exists in this project"
            );
        }

        return taskListRepository.save(taskList);
    }

    public TaskList updateTaskList(TaskList taskList, Project project, String title) {
        if (taskListRepository.findByProjectIdAndName(project.getId(), taskList.getTitle()).isPresent()) {
            throw new IllegalArgumentException("TaskList with name '" + taskList.getTitle() + "' already exists in this project");
        }
        taskList.setTitle(title);
        return taskListRepository.save(taskList);

    }

    public void deleteTaskList(Long id, Project project) {
        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TaskList not found with id: " + id));
        taskListRepository.delete(taskList);
    }

}
