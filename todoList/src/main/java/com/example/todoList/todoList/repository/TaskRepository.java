package com.example.todoList.todoList.repository;

import com.example.todoList.todoList.enums.Status;
import com.example.todoList.todoList.model.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TaskRepository extends ListPagingAndSortingRepository<TaskEntity, Long>, JpaRepository<TaskEntity, Long> {

    Page<TaskEntity> findAll(Pageable pageable);

    @Query("SELECT t FROM TaskEntity t WHERE t.finalDate BETWEEN :start AND :end")
    Page<TaskEntity> findByFinalDateBetween(Pageable pageable,
                                             @Param("start") LocalDate startDate,
                                             @Param("end") LocalDate endDate);

    Page<TaskEntity> findByStatus(Pageable pageable, Status status);

    Page<TaskEntity> findByCurrent(Pageable pageable, Boolean current);
}
