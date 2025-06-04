package com.example.todoList.todoList.model;


import com.example.todoList.todoList.enums.Status;
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

    private String description;

    private LocalDate createdDate;

    private LocalDate finalDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Boolean current;

    public TaskEntity(String description, LocalDate finalDate, Boolean current) {
        this.description = description;
        this.finalDate = finalDate;
        this.current = current;
        this.createdDate = LocalDate.now();
        this.status = Status.PENDING;
    }


}
