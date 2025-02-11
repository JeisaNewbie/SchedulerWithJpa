package com.example.schedulerwithjpa.controller;

import com.example.schedulerwithjpa.dto.request.DeleteToDoRequestDto;
import com.example.schedulerwithjpa.dto.request.ToDoRequestDto;
import com.example.schedulerwithjpa.dto.request.UpdateToDoRequestDto;
import com.example.schedulerwithjpa.dto.response.CreateToDoResponseDto;
import com.example.schedulerwithjpa.dto.response.ToDosResponseDto;
import com.example.schedulerwithjpa.dto.response.UpdateToDoResponseDto;
import com.example.schedulerwithjpa.entity.ToDoEntity;
import com.example.schedulerwithjpa.service.ToDoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    // 모든 일정 조회
    @GetMapping("/todos")
    public ResponseEntity<List<ToDosResponseDto>> findAllToDos() {
        return ResponseEntity.ok(toDoService.findAllToDo());
    }

    // 단일 일정 조회
    @GetMapping("/todos/{id}")
    public ResponseEntity<ToDosResponseDto> findToDoById(
            @Valid
            @Min(value = 1, message = "ID 값은 1 이상이어야 합니다.")
            @PathVariable Long id
    ) {
    return ResponseEntity.ok().build();
    }

    // 일정 생성
    @PostMapping("/todos")
    public ResponseEntity<CreateToDoResponseDto> saveToDo(
            @Valid
            @RequestBody
            ToDoRequestDto dto
    ) {
        return ResponseEntity.ok(toDoService.saveToDo(
                dto.getUserId(),
                dto.getDate(),
                dto.getTitle(),
                dto.getToDo()));
    }

    // 일정 정보 수정
    @PatchMapping("/todos")
    public ResponseEntity<UpdateToDoResponseDto> updateToDo(
            @Valid
            @RequestBody
            UpdateToDoRequestDto dto
    ) {
        toDoService.updateToDo(
                dto.getId(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getDate(),
                dto.getTitle(),
                dto.getToDo()
        );

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todos")
    public ResponseEntity<Void> deleteToDo(
            @Valid
            @RequestBody
            DeleteToDoRequestDto dto
    ) {
        toDoService.deleteToDo(
                dto.getEmail(),
                dto.getPassword(),
                dto.getId()
        );

        return ResponseEntity.noContent().build();
    }


}
