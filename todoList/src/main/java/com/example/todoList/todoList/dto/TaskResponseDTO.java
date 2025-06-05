package com.example.todoList.todoList.dto;

import com.example.todoList.todoList.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record TaskResponseDTO(
        String description,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate finalDate,
        Boolean current,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate createDate,
        Status status
) {
}
