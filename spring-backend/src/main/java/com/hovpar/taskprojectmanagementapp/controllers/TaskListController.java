package com.hovpar.taskprojectmanagementapp.controllers;

import com.hovpar.taskprojectmanagementapp.models.Project;
import com.hovpar.taskprojectmanagementapp.models.TaskList;
import com.hovpar.taskprojectmanagementapp.services.ProjectService;
import com.hovpar.taskprojectmanagementapp.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/api/projects/{projectId}/task-lists")
public class TaskListController {

    private final TaskListService taskListService;

    @Autowired
    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping
    public ResponseEntity<List<TaskList>> getAllTaskLists(@PathVariable Long projectId) {
        List<TaskList> taskLists = taskListService.getTaskListsByProject(projectId);
        return new ResponseEntity<>(taskLists, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskList> createTaskList(Project project, @RequestBody TaskList taskList) {
        try {
            TaskList createdTaskList = taskListService.createTaskList(taskList, project);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskList);


        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
