package com.nihir.tasks.services;

import com.nihir.tasks.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> listAllTaskLists();
    TaskList createTaskList(TaskList taskList);
}
