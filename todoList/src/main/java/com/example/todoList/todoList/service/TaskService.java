package com.example.todoList.todoList.service;

import com.example.todoList.todoList.config.Mapper;
import com.example.todoList.todoList.dto.TaskRequestDTO;
import com.example.todoList.todoList.dto.TaskResponseDTO;
import com.example.todoList.todoList.enums.Status;
import com.example.todoList.todoList.exception.NotFoundException;
import com.example.todoList.todoList.model.TaskEntity;
import com.example.todoList.todoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
public class TaskService implements CrudService<TaskEntity, Long> {

    @Autowired
    private TaskRepository taskRepository;

    public Page<TaskResponseDTO> getAllTasks(Pageable pageable) {
        return Mapper.toPageResponse(taskRepository.findAll(pageable));
    }

    @Override
    public void create(TaskRequestDTO entity) {
        taskRepository.save(Mapper.toEntity(entity));
    }

    @Override
    public TaskResponseDTO findById(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found task for id " + id));
        return Mapper.toResponse(taskEntity);
    }

    @Override
    public Page<TaskResponseDTO> findAll(Pageable pageable) {
        return Mapper.toPageResponse(taskRepository.findAll(pageable));
    }

    @Override
    public Page<TaskResponseDTO> findByFinalDate(Pageable pageable, LocalDate startDate, LocalDate endDate) {
        return Mapper.toPageResponse(taskRepository.findAByFinalDateBetween(pageable, startDate ,endDate));
    }

    @Override
    public Page<TaskResponseDTO> findByStatus(Pageable pageable, Status status) {
        return Mapper.toPageResponse(taskRepository.findByStatus(pageable, status));
    }

    @Override
    public Page<TaskResponseDTO> findByCurrent(Pageable pageable, Boolean current) {
        return Mapper.toPageResponse(taskRepository.findByCurrent(pageable, current));
    }
}
