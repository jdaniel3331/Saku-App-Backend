package com.jdaniel.sakuappapi.task.model

import com.jdaniel.sakuappapi.auth.model.User
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.time.LocalDate

@Entity
@Table(name = "tasks", schema = "task")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", unique = true, nullable = false)
    private var taskId: Long? = null

    @Column(name = "title", nullable = false)
    private var title: String? = null

    @Column(name = "description")
    private var description: String? = null

    @Column(name = "created_at", nullable = false)
    private var cratedAt: LocalDate? = null

    @Column(name = "due_date")
    private var dueDate: LocalDate? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private var category: Category? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_state", nullable = false)
    private var taskState: TaskState? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_level", nullable = false)
    private var priorityLevel: PriorityLevel? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private var user: User? = null
}
