package com.example.schedulerwithjpa.service;

import com.example.schedulerwithjpa.dto.response.CreateToDoResponseDto;
import com.example.schedulerwithjpa.dto.response.ToDosResponseDto;
import com.example.schedulerwithjpa.entity.ToDoEntity;
import com.example.schedulerwithjpa.entity.UserEntity;
import com.example.schedulerwithjpa.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final UserEntityService userEntityService;

    public List<ToDosResponseDto> findAllToDo() {
        return  userEntityService.findAllUserEntity()
                .stream()
                .map(ToDosResponseDto::new)
                .toList();
    }

    public CreateToDoResponseDto saveToDo(Long userId, LocalDate date, String title, String toDo) {
        
        UserEntity savedUser = userEntityService.findUserEntityByUserIdOrElseThrow(userId);

        ToDoEntity toDoEntity = ToDoEntity.builder()
                .date(date)
                .title(title)
                .toDo(toDo)
                .user(savedUser)
                .build();

        ToDoEntity savedToDo = toDoRepository.save(toDoEntity);
        
        return new CreateToDoResponseDto(savedToDo);
    }

    @Transactional
    public void updateToDo(Long id, String email, Long password, LocalDate date, String title, String toDo) {
        
        ToDoEntity savedToDo = toDoRepository.findByIdOrElseThrow(id);

        validateUser(email, password, savedToDo);

        savedToDo.updateToDo(date, title, toDo);
    }

    public void deleteToDo(String email, Long password, Long id) {
        
        ToDoEntity savedToDo = toDoRepository.findByIdOrElseThrow(id);

        validateUser(email, password, savedToDo);

        toDoRepository.deleteById(id);
    }

    private void validateUser(String email, Long password, ToDoEntity savedToDo) {
        if (!savedToDo.getUser().getEmail().equals(email) || !savedToDo.getUser().getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 이메일 혹은 비밀번호 입니다.");
        }
    }
}
