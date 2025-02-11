package com.example.schedulerwithjpa.service;

import com.example.schedulerwithjpa.dto.response.CreateToDoResponseDto;
import com.example.schedulerwithjpa.dto.response.ToDosResponseDto;
import com.example.schedulerwithjpa.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final UserService userService;

    public List<ToDosResponseDto> findAllToDo() {
        return  userService.findAllUserEntity()
                .stream()
                .map(ToDosResponseDto::new)
                .toList();
    }

    public CreateToDoResponseDto saveToDo(Long userId, LocalDate date, String title, String toDo) {
        return null;
    }
}
