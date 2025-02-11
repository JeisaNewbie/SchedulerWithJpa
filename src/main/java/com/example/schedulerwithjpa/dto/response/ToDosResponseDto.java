package com.example.schedulerwithjpa.dto.response;

import com.example.schedulerwithjpa.entity.ToDoEntity;
import com.example.schedulerwithjpa.entity.UserEntity;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ToDosResponseDto {
    private Long userId;

    private String username;

    private Integer toDoNum;

    private List<ToDo> toDos;

    public ToDosResponseDto(UserEntity userEntity) {
        this.userId = userEntity.getId();
        this.username = userEntity.getUsername();
        this.toDos = ToDo.ofList(userEntity.getToDoEntities());
        this.toDoNum = toDos.size();
    }

    @Getter
    private static class ToDo {

        private final Long toDoId;

        private final String title;

        private final LocalDate date;

        private final Integer commentNum;

        private ToDo(Long toDoId, String title, LocalDate date, Integer commentNum) {
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
                    toDoEntity.getCommentEntities().size());
        }
    }
}
