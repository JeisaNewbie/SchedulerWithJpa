package com.example.schedulerwithjpa.dto.response;

import com.example.schedulerwithjpa.entity.ToDoEntity;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CreateToDoResponseDto {

    private Long id;

    private Long userId;

    private String username;

    private String title;

    private String toDo;

    private LocalDate date;

    private LocalDateTime createdAt;

    public CreateToDoResponseDto(ToDoEntity savedToDo) {
        this.id = savedToDo.getId();
        this.userId = savedToDo.getUser().getId();
        this.username = savedToDo.getUser().getUsername();
        this.title = savedToDo.getTitle();
        this.toDo = savedToDo.getToDo();
        this.date = savedToDo.getDate();
        this.createdAt = savedToDo.getCreatedAt();
    }
}
