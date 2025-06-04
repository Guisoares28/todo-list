package com.example.todoList.todoList.config;
import com.example.todoList.todoList.dto.TaskRequestDTO;
import com.example.todoList.todoList.dto.TaskResponseDTO;
import com.example.todoList.todoList.model.TaskEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    public TaskEntity toEntity(TaskRequestDTO dto) {
        return new TaskEntity(
                dto.description(),
                dto.finalDate(),
                dto.current()
        );
    }

    public TaskResponseDTO toResponse(TaskEntity task) {
        return new TaskResponseDTO(
                task.getDescription(),
                task.getFinalDate(),
                task.getCurrent(),
                task.getCreatedDate(),
                task.getStatus()
        );
    }

    public List<TaskResponseDTO> toListResponse(List<TaskEntity> taskEntities){
        return taskEntities.stream()
                .map(this::toResponse)
                .toList();
    }

    @Bean
    public Mapper mapper() {
        return new Mapper();
    }

}
