package com.hovpar.taskprojectmanagementapp.repositories;

import com.hovpar.taskprojectmanagementapp.entities.TaskList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {

    @Query("SELECT t FROM TaskList t WHERE t.project.id = :projectId AND t.title = :name")
    Optional<TaskList> findByProjectIdAndName(@Param("projectId") Long projectId, @Param("name") String name);

    @Query("SELECT t FROM TaskList t WHERE t.project.id = :projectId")
    List<TaskList> findAllByProjectId(@Param("projectId") Long projectId);

    Optional<Object> findByProjectIdAndTitle(Long projectId, String title);
}
