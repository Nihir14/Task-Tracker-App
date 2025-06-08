package com.nihir.task.mappers.impl;

import com.nihir.task.domain.dtos.TaskDto;
import com.nihir.task.domain.entities.Task;
import com.nihir.task.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                null,
                null,
                taskDto.status(),
                taskDto.priority(),
                null

        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}

