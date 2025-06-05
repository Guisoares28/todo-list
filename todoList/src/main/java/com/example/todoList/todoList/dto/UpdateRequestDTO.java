package com.example.todoList.todoList.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;


import java.time.LocalDate;

public record UpdateRequestDTO(

        @Schema(description = "field for description task", example = "Pay to bill water")
        String description,

        @Schema(description = "field for finalization task date", example = "05/07/2025")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate finalDate
) {
}
