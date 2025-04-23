package com.jdaniel.sakuappapi.task.service;

import com.jdaniel.sakuappapi.task.model.Task;
import com.jdaniel.sakuappapi.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public boolean createTask(Task task) {
        return true;
    }
}
