package com.hovpar.taskprojectmanagementapp.repositories;

import com.hovpar.taskprojectmanagementapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
