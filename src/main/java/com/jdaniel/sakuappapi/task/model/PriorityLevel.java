package com.jdaniel.sakuappapi.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "priority_levels", schema = "task")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PriorityLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short priorityLevelId;
    @Column(name = "level", nullable = false)
    private String level;

    @OneToMany(mappedBy = "priorityLevel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
}
