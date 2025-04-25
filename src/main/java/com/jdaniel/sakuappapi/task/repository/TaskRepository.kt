package com.jdaniel.sakuappapi.task.repository

import com.jdaniel.sakuappapi.user.model.User
import com.jdaniel.sakuappapi.task.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository: JpaRepository<Task, Long> {

    //get tasks by userId
    fun getTasksByUser(user: User): List<Task>
}