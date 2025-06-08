package com.nihir.task.domain.dtos;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
