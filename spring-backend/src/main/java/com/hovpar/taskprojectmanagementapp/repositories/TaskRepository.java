package com.hovpar.taskprojectmanagementapp.repositories;

import com.hovpar.taskprojectmanagementapp.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTasksByTaskListId(Long taskListId);

}
