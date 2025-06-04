package com.example.todoList.todoList.controller;

import com.example.todoList.todoList.dto.TaskRequestDTO;
import com.example.todoList.todoList.model.TaskEntity;
import com.example.todoList.todoList.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@Tag(name="Tasks", description = "operations related to task management")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Create a new Task", description = "save new task in database with initial status PENDING")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created task successfully"),
            @ApiResponse(responseCode = "400", description = "invalid request")
    })
    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody @Valid TaskRequestDTO taskRequestDTO) {
        taskService.saveTask(taskRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<TaskEntity>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok().body(taskService.getAllTasks(page,size));
    }

}
