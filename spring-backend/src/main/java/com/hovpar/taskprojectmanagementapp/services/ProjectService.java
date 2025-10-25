package com.hovpar.taskprojectmanagementapp.services;

import com.hovpar.taskprojectmanagementapp.entities.Project;
import com.hovpar.taskprojectmanagementapp.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        // Check if project with same name already exists
        if (projectRepository.findByName(project.getName()).isPresent()) {
            throw new IllegalArgumentException("Project with name '" + project.getName() + "' already exists");
        }
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project projectTitle) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + id));

        // Check if another project with the same name exists (excluding current
        // project)
        Optional<Project> existingProject = projectRepository.findByName(projectTitle.getName());
        if (existingProject.isPresent() && !existingProject.get().getId().equals(id)) {
            throw new IllegalArgumentException("Project with name '" + projectTitle.getName() + "' already exists");
        }

        project.setName(projectTitle.getName());
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + id));
        projectRepository.delete(project);
    }

}
