package com.example.todoList.todoList.service;

import com.example.todoList.todoList.dto.TaskRequestDTO;
import com.example.todoList.todoList.dto.TaskResponseDTO;
import com.example.todoList.todoList.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

public interface CrudService<T, ID> {

    void create(TaskRequestDTO entity);

    TaskResponseDTO findById(Long id);

    Page<TaskResponseDTO> findAll(Pageable pageable);

    Page<TaskResponseDTO> findByFinalDate(Pageable pageable, LocalDate startDate, LocalDate endDate);

    Page<TaskResponseDTO> findByStatus(Pageable pageable, Status status);

    Page<TaskResponseDTO> findByCurrent(Pageable pageable ,Boolean current);


}
