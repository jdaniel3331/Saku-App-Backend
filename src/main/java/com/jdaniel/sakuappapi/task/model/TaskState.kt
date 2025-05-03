package com.jdaniel.sakuappapi.task.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Table(name = "task_states", schema = "task")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class TaskState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var taskStateId: Short? = null
    var state: String? = null

    @OneToMany(mappedBy = "taskState", cascade = [CascadeType.ALL], orphanRemoval = true)
    var tasks: MutableList<Task?>? = null
}
