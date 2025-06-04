package com.nihir.tasks.repos;

import com.nihir.tasks.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepo extends JpaRepository<Task, UUID> {
    List<Task> findByTaskListId(UUID taskListId);
    Optional<Task> findByIdAndTaskListId(UUID id, UUID taskListId);
    void deleteByIdAndTaskListId(UUID id, UUID taskListId);
}
