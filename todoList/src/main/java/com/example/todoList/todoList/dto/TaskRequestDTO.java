package com.example.todoList.todoList.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record TaskRequestDTO(
        @NotBlank(message = "description field is not null")
        @NotEmpty(message = "description field is not empty")
        String description,

        @NotNull(message = "final date field is not null")
        @Future(message = "The date must be in the future")
        LocalDate finalDate,

        @NotNull(message = "current field is not null")
        Boolean current
) {
}
