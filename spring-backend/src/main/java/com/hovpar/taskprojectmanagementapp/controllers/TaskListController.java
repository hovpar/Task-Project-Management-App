package com.hovpar.taskprojectmanagementapp.controllers;

import com.hovpar.taskprojectmanagementapp.entities.TaskList;
import com.hovpar.taskprojectmanagementapp.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/{projectId}/task-lists")
public class TaskListController {

    private final TaskListService taskListService;

    @Autowired
    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping
    public ResponseEntity<List<TaskList>> getTaskListsByProject(@PathVariable Long projectId) {
        List<TaskList> taskLists = taskListService.getTaskListsByProject(projectId);
        return new ResponseEntity<>(taskLists, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskList> createTaskList(@PathVariable Long projectId, @RequestBody TaskList taskList) {
        try {
            TaskList createdTaskList = taskListService.createTaskList(projectId, taskList);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskList);


        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping ("/{id}")
    public ResponseEntity<TaskList> updateTaskList(@PathVariable Long id, @RequestBody String newTitle) {
        try{
         TaskList updatedTaskList = taskListService.updateTaskList(id, newTitle);
            return ResponseEntity.ok(updatedTaskList);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<TaskList> deleteTaskList(@PathVariable Long id){
       taskListService.deleteTaskList(id);
       return ResponseEntity.ok().build();
    }

}
