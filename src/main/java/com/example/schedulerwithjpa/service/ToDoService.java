package com.example.schedulerwithjpa.service;

import com.example.schedulerwithjpa.dto.response.CreateToDoResponseDto;
import com.example.schedulerwithjpa.dto.response.GetToDoResponseDto;
import com.example.schedulerwithjpa.dto.response.GetToDosResponseDto;
import com.example.schedulerwithjpa.entity.ToDoEntity;
import com.example.schedulerwithjpa.entity.UserEntity;
import com.example.schedulerwithjpa.repository.ToDoRepository;
import com.example.schedulerwithjpa.util.PasswordEncoder;
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

    public List<GetToDosResponseDto> findAllToDo() {

        return  userEntityService.findAllUserEntity()
                .stream()
                .map(GetToDosResponseDto::new)
                .toList();
    }

    public GetToDoResponseDto findToDoByToDoId(Long id) {
        return new GetToDoResponseDto(toDoRepository.findByIdOrElseThrow(id));
    }

    public CreateToDoResponseDto saveToDo(Long userId, LocalDate date, String title, String toDo) {
        
        UserEntity savedUserEntity = userEntityService.findUserEntityByUserIdOrElseThrow(userId);

        ToDoEntity toDoEntity = ToDoEntity.builder()
                .date(date)
                .title(title)
                .toDo(toDo)
                .userEntity(savedUserEntity)
                .build();

        return new CreateToDoResponseDto(toDoRepository.save(toDoEntity));
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

    private void validateUser(String email, Long password, ToDoEntity toDoEntity) {

        UserEntity toDoUser = toDoEntity.getUserEntity();
        UserEntity user = userEntityService.validateUserOrElseThrow(email, password.toString());

        if (!toDoUser.equals(user)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 이메일 혹은 비밀번호 입니다.");
        }
    }
}
