package com.example.schedulerwithjpa.repository;

import com.example.schedulerwithjpa.entity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoEntity, Long> {
    default ToDoEntity findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정이 존재하지 않습니다."));
    }
}
