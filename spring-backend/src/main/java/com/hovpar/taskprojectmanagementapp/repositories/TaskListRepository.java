package com.hovpar.taskprojectmanagementapp.repositories;

import com.hovpar.taskprojectmanagementapp.models.TaskList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskListRepository {

    List<TaskList> findAll();
}
