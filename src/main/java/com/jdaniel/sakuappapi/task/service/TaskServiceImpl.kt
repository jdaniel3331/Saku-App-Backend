package com.jdaniel.sakuappapi.task.service

import com.jdaniel.sakuappapi.user.repository.UserRepository
import com.jdaniel.sakuappapi.common.exception.NotFoundedException
import com.jdaniel.sakuappapi.task.model.Task
import com.jdaniel.sakuappapi.task.model.dto.CreateTaskDto
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

    override fun createTask(task: CreateTaskDto): String {

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

    override fun getAllTasks(userId: Long): List<TaskDto> {
        val mapedTasks = mutableListOf<TaskDto>()
        val user = userRepository?.findById(userId)?.orElse(null)
        if (user == null) throw NotFoundedException("User not found", HttpStatus.NOT_FOUND.name,HttpStatus.NOT_FOUND.value())

        val tasks = taskRepository?.getTasksByUser(user)
        if (tasks.isNullOrEmpty()) throw NotFoundedException("User has no tasks", HttpStatus.NOT_FOUND.name,HttpStatus.NOT_FOUND.value())

        for (task in tasks) {
            val taskDto = TaskDto(
                taskId = task.taskId!!,
                title = task.title!!,
                description = task.description?: "",
                createdAt = task.cratedAt!!,
                dueDate = task.dueDate,
                category = task.category?.categoryId,
                taskState = task.taskState?.taskStateId!!,
                priorityLevel = task.priorityLevel?.priorityLevelId!!
            )
            mapedTasks.add(taskDto)
        }

        return mapedTasks

    }

    override fun deteleTask(taskId: Long): String {
        val isTaskPresent = taskRepository?.existsByTaskId(taskId)
        if (isTaskPresent == false) throw NotFoundedException("Task not found", HttpStatus.NOT_FOUND.name,HttpStatus.NOT_FOUND.value())

        taskRepository?.deleteByTaskId(taskId)

        return "Task deleted successfully"

    }

    override fun changeTaskTitle(taskId: Long, newTitle: String): String {
        val isTaskPresent = taskRepository?.existsByTaskId(taskId)
        if (isTaskPresent == false) throw NotFoundedException("Task not found", HttpStatus.NOT_FOUND.name,HttpStatus.NOT_FOUND.value())

        taskRepository?.updateTitleByTaskId(newTitle, taskId)

        return "Task title updated successfully"
    }
}