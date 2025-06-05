package com.example.todoList.todoList.controller;

import com.example.todoList.todoList.dto.TaskRequestDTO;
import com.example.todoList.todoList.dto.TaskResponseDTO;
import com.example.todoList.todoList.model.TaskEntity;
import com.example.todoList.todoList.service.CrudService;
import com.example.todoList.todoList.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/task")
@Tag(name="Tasks", description = "operations related to task management")
public class TaskController {

    @Autowired
    private CrudService<TaskEntity, Long> taskService;

    @Operation(summary = "Create a new Task", description = "save new task in database with initial status PENDING")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created task successfully"),
            @ApiResponse(responseCode = "400", description = "invalid request")
    })
    @PostMapping
    public ResponseEntity<Void> saveTasks(@RequestBody @Valid TaskRequestDTO taskRequestDTO) {
        taskService.create(taskRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get task for id", description = "Get task for Id, case id not exists then return NotFoundException")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Task for id Successfully" ),
            @ApiResponse(responseCode = "404", description = "Task id not found")
    })
    @GetMapping("/forId/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskForId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }


    @Operation(summary = "Get all tasks for page", description = "Get all tasks, \n" +
            "informing the page in the url and size page")
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks(Pageable pageable) {
        return ResponseEntity.ok().body(taskService.findAll(pageable).getContent());
    }

    @GetMapping("/dates")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasksForBetWeenDates(@RequestParam(value = "startDate")LocalDate startDate,
                                                                            @RequestParam(value = "endDate")LocalDate endDate,
                                                                            Pageable pageable) {
        return ResponseEntity.ok().body(taskService.findByFinalDate(pageable, startDate, endDate).getContent());
    }

}
