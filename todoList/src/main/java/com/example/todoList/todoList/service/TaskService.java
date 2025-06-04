package com.example.todoList.todoList.service;

import com.example.todoList.todoList.config.Mapper;
import com.example.todoList.todoList.dto.TaskRequestDTO;
import com.example.todoList.todoList.dto.TaskResponseDTO;
import com.example.todoList.todoList.model.TaskEntity;
import com.example.todoList.todoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void saveTask(TaskRequestDTO taskRequestDTO){
        taskRepository.save(Mapper.toEntity(taskRequestDTO));
    }

    public Page<TaskEntity> getAllTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return taskRepository.findAll(pageable);

    }

}
