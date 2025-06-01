package com.nihir.tasks.domain.dtos;

import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        double progress,
        List<TaskDto> tasks
) {
}
