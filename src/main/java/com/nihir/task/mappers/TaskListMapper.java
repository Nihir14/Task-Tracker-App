package com.nihir.task.mappers;

import com.nihir.task.domain.dtos.TaskListDto;
import com.nihir.task.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);

}

