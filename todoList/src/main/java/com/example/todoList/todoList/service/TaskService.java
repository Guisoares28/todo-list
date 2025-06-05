package com.example.todoList.todoList.service;

import com.example.todoList.todoList.config.Mapper;
import com.example.todoList.todoList.dto.TaskRequestDTO;
import com.example.todoList.todoList.dto.TaskResponseDTO;
import com.example.todoList.todoList.dto.UpdateRequestDTO;
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
        Page<TaskResponseDTO> content = Mapper.toPageResponse(taskRepository.findByFinalDateBetween(pageable, startDate ,endDate));
        if(content.isEmpty()){
            throw new NotFoundException("Tasks not found for the given range");
        }
        return content;
    }

    @Override
    public Page<TaskResponseDTO> findByStatus(Pageable pageable, Status status) {
        Page<TaskResponseDTO> content = Mapper.toPageResponse(taskRepository.findByStatus(pageable, status));
        if(content.isEmpty()){
            throw new NotFoundException("Tasks not found for the given status");
        }
        return content;
    }

    @Override
    public Page<TaskResponseDTO> findByCurrent(Pageable pageable, Boolean current) {
        Page<TaskResponseDTO> content = Mapper.toPageResponse(taskRepository.findByCurrent(pageable, current));
        if(content.isEmpty()){
            throw new NotFoundException("Tasks not found for the given current");
        }
        return content;
    }

    @Override
    public void update(Long id, UpdateRequestDTO updateDTO) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found with " + id));
        if(updateDTO.description() != null && !updateDTO.description().isEmpty()){
            task.setDescription(updateDTO.description());
        }
        if(updateDTO.finalDate() != null && !updateDTO.finalDate().isBefore(LocalDate.now())){
            task.setFinalDate(updateDTO.finalDate());
        }
        taskRepository.save(task);
    }

    @Override
    public void updateStatus(Long id, Status status) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found task for id" + id));
        task.setStatus(status);
        taskRepository.save(task);
    }

    @Override
    public void updateCurrent(Long id, Boolean current) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found task for id" + id));
        task.setCurrent(current);
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        if(!taskRepository.existsById(id)){
            throw new NotFoundException("Not found task for id" + id);
        }
        taskRepository.deleteById(id);
    }
}
