package com.jdaniel.sakuappapi.task.repository

import com.jdaniel.sakuappapi.task.model.TaskState
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskStateRepository: JpaRepository<TaskState, Short> {
}