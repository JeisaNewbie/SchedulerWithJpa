package com.example.schedulerwithjpa.dto.response;

import com.example.schedulerwithjpa.entity.ToDoEntity;
import com.example.schedulerwithjpa.entity.User;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ToDosResponseDto {
    private Long userId;

    private String username;

    private Long toDoNum;

    private List<ToDo> toDos;

    public ToDosResponseDto(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.toDos = ToDo.ofList(null); //user.getToDoEntities
    }

    private static class ToDo {

        private Long toDoId;

        private String title;

        private LocalDate date;

        private Long commentNum;

        private ToDo(Long toDoId, String title, LocalDate date, Long commentNum) {
            this.toDoId = toDoId;
            this.title = title;
            this.date = date;
            this.commentNum = commentNum;
        }

        private static List<ToDo> ofList(List<ToDoEntity> toDoEntities) {
            return toDoEntities.stream()
                    .map(ToDo::of)
                    .toList();
        }

        private static ToDo of(ToDoEntity toDoEntity) {
            return new ToDo(
                    toDoEntity.getId(),
                    toDoEntity.getTitle(),
                    toDoEntity.getDate(),
                    null);
        }
    }
}
