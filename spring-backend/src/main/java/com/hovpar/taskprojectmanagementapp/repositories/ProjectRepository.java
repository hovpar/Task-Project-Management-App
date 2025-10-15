package com.hovpar.taskprojectmanagementapp.repositories;

import com.hovpar.taskprojectmanagementapp.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByName(String name);
}
