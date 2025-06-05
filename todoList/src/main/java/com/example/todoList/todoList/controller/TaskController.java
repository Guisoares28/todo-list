package com.example.todoList.todoList.controller;

import com.example.todoList.todoList.dto.TaskRequestDTO;
import com.example.todoList.todoList.dto.TaskResponseDTO;
import com.example.todoList.todoList.dto.UpdateRequestDTO;
import com.example.todoList.todoList.enums.Status;
import com.example.todoList.todoList.model.TaskEntity;
import com.example.todoList.todoList.service.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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

    @Operation(summary = "Get all tasks for between init Date and End date", description = "Get all tasks for between dates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succesfully Request"),
            @ApiResponse(responseCode = "404", description = "Tasks not found or EmptyList")
    })
    @GetMapping("/dates")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasksForBetWeenDates(@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate,
                                                                            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate,
                                                                            Pageable pageable) {
        return ResponseEntity.ok().body(taskService.findByFinalDate(pageable, startDate, endDate).getContent());
    }

    @Operation(summary = "Get task for status", description = "Find tasks for status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Request"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/forStatus/{status}")
    public ResponseEntity<List<TaskResponseDTO>> getAllTaskForStatus(@PathVariable Status status, Pageable pageable) {
        return ResponseEntity.ok().body(taskService.findByStatus(pageable,status).getContent());
    }

    @Operation(summary = "Get task for current", description = "Find tasks for current")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Request"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/forCurrent/{current}")
    public ResponseEntity<List<TaskResponseDTO>> getAllTaskForCurrent(@PathVariable Boolean current, Pageable pageable) {
        return ResponseEntity.ok().body(taskService.findByCurrent(pageable,current).getContent());
    }

    @Operation(summary = "update task", description = "Update task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Request"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateTask(@RequestBody @Valid UpdateRequestDTO updateDTO,
                                           @PathVariable Long id) {
        taskService.update(id,updateDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "update task status", description = "Update task status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Request"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping("/update/status/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id,
                                             @RequestParam(value = "status") Status status) {
        taskService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "update task current", description = "Update task current")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Request"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping("/update/current/{id}")
    public ResponseEntity<Void> updateCurrent(@PathVariable Long id,
                                             @RequestParam(value = "current") Boolean current) {
        taskService.updateCurrent(id, current);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

}
