package com.hovpar.taskprojectmanagementapp.repositories;

import com.hovpar.taskprojectmanagementapp.models.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTasksByTaskListId(Long taskListId, Sort sort);

    Optional<Task> findTaskById(Long taskId);
}
