package com.jdaniel.sakuappapi.task.repository

import com.jdaniel.sakuappapi.user.model.User
import com.jdaniel.sakuappapi.task.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface TaskRepository: JpaRepository<Task, Long> {

    //get tasks by userId
    fun getTasksByUser(user: User): List<Task>
    fun existsByTaskId(taskId: Long): Boolean
    @Transactional
    fun deleteByTaskId(taskId: Long)

    @Transactional
    @Modifying
    @Query("UPDATE Task t SET t.title = ?1 WHERE t.taskId = ?2")
    fun updateTitleByTaskId(title: String, taskId: Long)
}