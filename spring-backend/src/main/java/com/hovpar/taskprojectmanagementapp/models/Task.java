package com.hovpar.taskprojectmanagementapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "tasks")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String title;

    private String description;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDate addedAt;
    // Many tasks belong to one TaskList
    @ManyToOne
    @JoinColumn(name = "task_list_id") // foreign key
    private TaskList taskList;
}
