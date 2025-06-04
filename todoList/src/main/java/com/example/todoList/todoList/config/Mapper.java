package com.example.todoList.todoList.config;
import com.example.todoList.todoList.dto.TaskRequestDTO;
import com.example.todoList.todoList.dto.TaskResponseDTO;
import com.example.todoList.todoList.model.TaskEntity;

import java.util.List;


public class Mapper {

    public static TaskEntity toEntity(TaskRequestDTO dto) {
        return new TaskEntity(
                dto.description(),
                dto.finalDate(),
                dto.current()
        );
    }

    public static TaskResponseDTO toResponse(TaskEntity task) {
        return new TaskResponseDTO(
                task.getDescription(),
                task.getFinalDate(),
                task.getCurrent(),
                task.getCreatedDate(),
                task.getStatus()
        );
    }

    public static List<TaskResponseDTO> toListResponse(List<TaskEntity> taskEntities){
        return taskEntities.stream()
                .map(Mapper::toResponse)
                .toList();
    }
}
