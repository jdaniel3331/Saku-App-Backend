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
    var priorityLevelId: Short? = null

    @Column(name = "level", nullable = false)
    var level: String? = null

    @OneToMany(mappedBy = "priorityLevel", cascade = [CascadeType.ALL], orphanRemoval = true)
    var tasks: MutableList<Task?>? = null
}
