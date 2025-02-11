package com.example.schedulerwithjpa.dto.response;

import com.example.schedulerwithjpa.entity.ToDoEntity;
import com.example.schedulerwithjpa.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserResponseDto {
    private Long id;

    private String username;

    private String email;

    private LocalDateTime createdAt;

    private List<ToDo> toDos;

    public UserResponseDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.email = userEntity.getEmail();
        this.createdAt = userEntity.getCreatedAt();
        this.toDos = ToDo.ofList(userEntity.getToDoEntities());
    }

    @Getter
    private static class ToDo {

        private final Long toDoId;

        private final String title;

        private final String toDo;

        private final LocalDate date;

        private ToDo(Long toDoId, String title, String toDo, LocalDate date) {
            this.toDoId = toDoId;
            this.title = title;
            this.toDo = toDo;
            this.date = date;
        }

        private static ToDo of(ToDoEntity toDoEntity) {
            return new ToDo(
                    toDoEntity.getId(),
                    toDoEntity.getTitle(),
                    toDoEntity.getToDo(),
                    toDoEntity.getDate()
            );
        }

        private static List<ToDo> ofList(List<ToDoEntity> toDoEntities) {
            return toDoEntities.stream()
                    .map(ToDo::of)
                    .toList();
        }
    }
}
