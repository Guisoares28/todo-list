package com.example.todoList.todoList.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record TaskRequestDTO(
        @NotBlank(message = "description field is not null")
        @NotEmpty(message = "description field is not empty")
        @Schema(description = "field for description task", example = "Pay to bill water")
        String description,

        @NotNull(message = "final date field is not null")
        @Future(message = "The date must be in the future")
        @JsonFormat(pattern = "dd/MM/yyyy")
        @Schema(description = "field for finalization task date", example = "05/07/2025")
        LocalDate finalDate,

        @Schema(description = "field for tasks currents", example = "true or false")
        @NotNull(message = "current field is not null")
        Boolean current
) {
}
