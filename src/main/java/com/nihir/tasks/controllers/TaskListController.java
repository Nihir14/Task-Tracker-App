package com.nihir.tasks.controllers;

import com.nihir.tasks.domain.dtos.TaskListDto;
import com.nihir.tasks.mappers.TaskListMapper;
import com.nihir.tasks.services.TaskListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listAllTaskLists() {
        return taskListService.listAllTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

}
