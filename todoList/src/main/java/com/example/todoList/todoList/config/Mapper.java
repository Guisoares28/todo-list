package com.example.todoList.todoList.config;


import com.example.todoList.todoList.model.TaskEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Mapper {


    @Bean
    public Mapper mapper() {
        return new Mapper();
    }

}
