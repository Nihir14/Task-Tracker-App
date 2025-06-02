package com.nihir.tasks.services.impl;

import com.nihir.tasks.domain.entities.TaskList;
import com.nihir.tasks.repos.TaskListRepo;
import com.nihir.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepo taskListRepo;

    public TaskListServiceImpl(TaskListRepo taskListRepo) {
        this.taskListRepo = taskListRepo;
    }

    @Override
    public List<TaskList> listAllTaskLists() {
        return taskListRepo.findAll();
    }
}
