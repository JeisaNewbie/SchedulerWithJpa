package com.example.schedulerwithjpa.service;

import com.example.schedulerwithjpa.entity.ToDoEntity;
import com.example.schedulerwithjpa.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoEntityService {

    private final ToDoRepository toDoRepository;

    public List<ToDoEntity> findAllToDoEntity() {
        return toDoRepository.findAll();
    }

    public ToDoEntity findToDoEntityByIdOrElseThrow(Long id) {
        return toDoRepository.findByIdOrElseThrow(id);
    }

}
