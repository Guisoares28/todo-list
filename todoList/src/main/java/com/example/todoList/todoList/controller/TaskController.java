package com.example.todoList.todoList.controller;

import com.example.todoList.todoList.dto.TaskRequestDTO;
import com.example.todoList.todoList.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@Tag(name="Tasks", description = "operations related to task management")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody @Valid TaskRequestDTO taskRequestDTO) {
        taskService.saveTask(taskRequestDTO);
        return ResponseEntity.ok().build();
    }

}
