package com.jdaniel.sakuappapi.task.repository

import com.jdaniel.sakuappapi.task.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository: JpaRepository<Category, Long> {
}