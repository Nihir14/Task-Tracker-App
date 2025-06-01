package com.nihir.tasks.mappers.impl;

import com.nihir.tasks.domain.dtos.TaskDto;
import com.nihir.tasks.domain.entities.Task;
import com.nihir.tasks.mappers.TaskMapper;
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

