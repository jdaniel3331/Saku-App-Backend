package com.jdaniel.sakuappapi.task.repository;

import com.jdaniel.sakuappapi.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
