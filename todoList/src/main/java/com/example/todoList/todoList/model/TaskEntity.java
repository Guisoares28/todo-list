package com.example.todoList.todoList.model;


import com.example.todoList.todoList.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_tasks")
@Getter
@Setter
@NoArgsConstructor
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "task description", example = "pay electricity bill")
    private String description;

    @Schema(description = "task created date, This value is default when constructing an instance", example = "04/06/2025")
    private LocalDate createdDate;

    @Schema(description = "task final date, This value represents the final date to perform the task", example = "20/07/2025")
    private LocalDate finalDate;

    @Schema(description = "The status represents the period in which our task is located. this value is default" +
            "when constructing as instance", example = "PEDING")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Schema(description = "recurring represents a bill that needs to be paid every month", example = "true or false")
    private Boolean current;

    public TaskEntity(String description, LocalDate finalDate, Boolean current) {
        this.description = description;
        this.finalDate = finalDate;
        this.current = current;
        this.createdDate = LocalDate.now();
        this.status = Status.PENDING;
    }


}
