package com.example.todoList.todoList.service;

import com.example.todoList.todoList.config.Mapper;
import com.example.todoList.todoList.dto.TaskRequestDTO;
import com.example.todoList.todoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void saveTask(TaskRequestDTO taskRequestDTO){
        taskRepository.save(Mapper.toEntity(taskRequestDTO));
    }

}
