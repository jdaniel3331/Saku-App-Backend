package com.jdaniel.sakuappapi.task.model;

import com.jdaniel.sakuappapi.auth.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "tasks", schema = "task")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", unique = true, nullable = false)
    private Long taskId;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "created_at", nullable = false)
    private LocalDate cratedAt;
    @Column(name = "due_date", nullable = true)
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_state", nullable = false)
    private TaskState taskState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_level", nullable = false)
    private PriorityLevel priorityLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
