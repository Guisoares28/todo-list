package com.example.todoList.todoList.dto;

import com.example.todoList.todoList.enums.Status;

import java.time.LocalDate;

public record TaskResponseDTO(
        String description,
        LocalDate finalDate,
        Boolean current,
        LocalDate createDate,
        Status status
) {
}
