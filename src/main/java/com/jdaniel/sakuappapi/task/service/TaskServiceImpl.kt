package com.jdaniel.sakuappapi.task.service

import com.jdaniel.sakuappapi.auth.repository.UserRepository
import com.jdaniel.sakuappapi.common.exception.NotFoundedException
import com.jdaniel.sakuappapi.task.model.PriorityLevel
import com.jdaniel.sakuappapi.task.model.Task
import com.jdaniel.sakuappapi.task.model.dto.TaskDto
import com.jdaniel.sakuappapi.task.repository.CategoryRepository
import com.jdaniel.sakuappapi.task.repository.PriorityLevelRepository
import com.jdaniel.sakuappapi.task.repository.TaskRepository
import com.jdaniel.sakuappapi.task.repository.TaskStateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TaskServiceImpl: TaskService {
    @Autowired
    private val taskRepository: TaskRepository? = null
    @Autowired
    private val categoryRepository: CategoryRepository? = null
    @Autowired
    private val taskStateRepository: TaskStateRepository? = null
    @Autowired
    private val priorityLevelRepository: PriorityLevelRepository? = null
    @Autowired
    private val userRepository: UserRepository? = null

    override fun createTask(task: TaskDto): String {

        val priorityLevel = priorityLevelRepository?.findById(task.priorityLevel)?.orElse(null)
        if (priorityLevel == null) throw NotFoundedException("Priority level not found", HttpStatus.NOT_FOUND.name,HttpStatus.NOT_FOUND.value())

        val user = userRepository?.findById(task.userId)?.orElse(null)
        if (user == null) throw NotFoundedException("User not found", HttpStatus.NOT_FOUND.name,HttpStatus.NOT_FOUND.value())

        val newTask = Task()
        newTask.title = task.title
        newTask.description = task.description
        newTask.dueDate = task.dueDate
        newTask.cratedAt = LocalDate.now()
        newTask.category = task.category.let { categoryRepository?.findById(it)?.orElse(null) }
        newTask.priorityLevel = priorityLevel
        newTask.taskState = taskStateRepository?.findById(1)?.orElse(null)
        newTask.user = user

        val savedTask: Task? = taskRepository?.save(newTask)

        return "Task ${savedTask?.title} created successfully"
    }
}