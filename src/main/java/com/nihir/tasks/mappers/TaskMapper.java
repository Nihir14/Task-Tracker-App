package com.nihir.tasks.mappers;

import com.nihir.tasks.domain.dtos.TaskDto;
import com.nihir.tasks.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);

}
