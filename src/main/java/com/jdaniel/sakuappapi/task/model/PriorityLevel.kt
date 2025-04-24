package com.jdaniel.sakuappapi.task.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Table(name = "priority_levels", schema = "task")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class PriorityLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var priorityLevelId: Short? = null

    @Column(name = "level", nullable = false)
    private var level: String? = null

    @OneToMany(mappedBy = "priorityLevel", cascade = [CascadeType.ALL], orphanRemoval = true)
    private var tasks: MutableList<Task?>? = null
}
