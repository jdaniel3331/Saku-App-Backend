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
class Task() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", unique = true, nullable = false)
    var taskId: Long? = null

    @Column(name = "title", nullable = false)
    var title: String? = null

    @Column(name = "description")
    var description: String? = null

    @Column(name = "created_at", nullable = false)
    var cratedAt: LocalDate? = null

    @Column(name = "due_date")
    var dueDate: LocalDate? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    var category: Category? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_state", nullable = false)
    var taskState: TaskState? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_level", nullable = false)
    var priorityLevel: PriorityLevel? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null
}
