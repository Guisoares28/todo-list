package com.example.todoList.todoList.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@Tag(name="Tasks", description = "operations related to task management")
public class TaskController {
}
