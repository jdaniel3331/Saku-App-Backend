package com.jdaniel.sakuappapi.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "task_states", schema = "task")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short taskId;
    private String state;

    @OneToMany(mappedBy = "taskState", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
}
