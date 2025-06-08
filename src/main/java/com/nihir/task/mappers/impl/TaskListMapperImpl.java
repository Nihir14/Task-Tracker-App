package com.nihir.task.mappers.impl;

import com.nihir.task.domain.dtos.TaskListDto;
import com.nihir.task.domain.entities.Task;
import com.nihir.task.domain.entities.TaskList;
import com.nihir.task.domain.entities.TaskStatus;
import com.nihir.task.mappers.TaskListMapper;
import com.nihir.task.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList())
                        .orElse(null),
                null, // created date should be set by the service layer
                null // updated date should be set by the service layer

        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable (taskList.getTasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::toDto)
                                .toList())
                        .orElse(null)


        );
    }

    private double calculateTaskListProgress(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            return 0.0;
        }
        long closedTaskCount = tasks.stream()
                .filter(task -> TaskStatus.CLOSE == task.getStatus())
                .count();
        return (double) closedTaskCount / tasks.size();
    }
}
