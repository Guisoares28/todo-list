package com.example.todoList.todoList.enums;

import io.swagger.v3.oas.annotations.media.Schema;

public enum Status {
    @Schema(description = "Pending task", example = "PENDING")
    PENDING,

    @Schema(description = "In progress task", example = "INPROGRESS")
    INPROGRESS,

    @Schema(description = "Finished task", example = "FINISHED")
    FINISHED
}
