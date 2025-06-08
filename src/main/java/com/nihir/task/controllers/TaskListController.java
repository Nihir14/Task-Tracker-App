package com.nihir.task.controllers;

import com.nihir.task.domain.dtos.TaskListDto;
import com.nihir.task.domain.entities.TaskList;
import com.nihir.task.mappers.TaskListMapper;
import com.nihir.task.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdtaskList = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdtaskList);
    }

    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDto> getTaskListById(@PathVariable("task_list_id") UUID taskListId) {
        return taskListService.getTaskListById(taskListId)
                .map(taskListMapper::toDto);
    }

    @PutMapping(path = "/{task_list_id}")
    public TaskListDto updateTaskList(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskListDto taskListDto
    ) {
        TaskList updatedTaskList = taskListService.updateTaskList(
                taskListId,
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(updatedTaskList);
    }

    @DeleteMapping(path = "/{task_list_id}")
    void deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
        taskListService.deleteTaskList(taskListId);
    }
}
