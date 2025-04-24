package com.jdaniel.sakuappapi.task.repository

import com.jdaniel.sakuappapi.task.model.PriorityLevel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PriorityLevelRepository: JpaRepository<PriorityLevel, Short> {

}