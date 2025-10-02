package com.hovpar.taskprojectmanagementapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tasks")
public class TaskList {

    @Id
    @Column(unique = true, nullable = false)
    private String title;

    // One TaskList can contain many Tasks
    @OneToMany(mappedBy = "taskList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();
}
