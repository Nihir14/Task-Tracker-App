package com.nihir.task.services.impl;

import com.nihir.task.domain.entities.Task;
import com.nihir.task.domain.entities.TaskList;
import com.nihir.task.domain.entities.TaskPriority;
import com.nihir.task.domain.entities.TaskStatus;
import com.nihir.task.repos.TaskListRepo;
import com.nihir.task.repos.TaskRepo;
import com.nihir.task.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepository;
    private final TaskListRepo taskListRepository;

    public TaskServiceImpl(TaskRepo taskRepository, TaskListRepo taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(null != task.getId()) {
            throw new IllegalArgumentException("Task already has an ID!");
        }
        if(null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task must have a title!");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task List ID provided!"));

        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                now,
                now,
                taskStatus,
                taskPriority,
                taskList
        );

        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTaskById(UUID taskListId, UUID taskId) {
        return taskRepository.findByIdAndTaskListId(taskId, taskListId);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if (null == task.getId()) {
            throw new IllegalArgumentException("Task must have an ID to be updated!");
        }
        if (!Objects.equals(taskId, task.getId())) {
            throw new IllegalArgumentException("Task ID in the path does not match the ID in the task object!");
        }
        if (null == task.getPriority()) {
            throw new IllegalArgumentException("Task must have a priority!");
        }
        if (null == task.getStatus()) {
            throw new IllegalArgumentException("Task must have a status!");
        }
        Task existingTask = taskRepository.findByIdAndTaskListId(taskId, taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found!"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByIdAndTaskListId(taskId, taskListId);
    }

}
