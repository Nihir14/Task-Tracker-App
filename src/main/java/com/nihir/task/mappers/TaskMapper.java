package com.nihir.task.mappers;

import com.nihir.task.domain.dtos.TaskDto;
import com.nihir.task.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);

}
