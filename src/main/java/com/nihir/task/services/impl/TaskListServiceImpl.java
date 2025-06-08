package com.nihir.task.services.impl;

import com.nihir.task.domain.entities.TaskList;
import com.nihir.task.repos.TaskListRepo;
import com.nihir.task.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepo taskListRepo;

    public TaskListServiceImpl(TaskListRepo taskListRepo) {
        this.taskListRepo = taskListRepo;
    }

    @Override
    public List<TaskList> listAllTaskLists() {
        return taskListRepo.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (null != taskList.getId()) {
            throw new IllegalArgumentException("TaskList already has an ID");
        }
        if (taskList.getTitle() == null || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("TaskList title cannot be null or blank");
        }
        LocalDateTime now = LocalDateTime.now();
        return taskListRepo.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskListById(UUID id) {
        return taskListRepo.findById(id);
    }

    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if (null == taskList.getId()) {
            throw new IllegalArgumentException("TaskList ID cannot be null");
        }

        if (!Objects.equals(taskList.getId(), taskListId)) {
            throw new IllegalArgumentException("TaskList ID does not match the provided ID");
        }

        TaskList existingTaskList = taskListRepo.findById(taskListId).orElseThrow(
                () -> new IllegalArgumentException("TaskList with ID " + taskListId + " does not exist")
        );

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepo.save(existingTaskList);
    }

    @Override
    public void deleteTaskList(UUID id) {
        taskListRepo.deleteById(id);
    }
}
