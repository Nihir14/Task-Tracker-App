package com.nihir.tasks.services;

import com.nihir.tasks.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> listAllTaskLists();
    TaskList createTaskList(TaskList taskList);
    Optional<TaskList> getTaskListById(UUID taskListId);
    TaskList updateTaskList(UUID id, TaskList taskList);
    void deleteTaskList(UUID id);
}
