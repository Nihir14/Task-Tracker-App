package com.nihir.tasks.mappers;

import com.nihir.tasks.domain.dtos.TaskListDto;
import com.nihir.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);

}

