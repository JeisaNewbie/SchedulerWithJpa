package com.example.schedulerwithjpa.dto.response;

import com.example.schedulerwithjpa.entity.CommentEntity;
import com.example.schedulerwithjpa.entity.ToDoEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GetAndUpdateCommentResponseDto {

    private final Long commentId;

    private final String comment;

    private final LocalDateTime modifiedAt;

    private final ToDo todo;

    public GetAndUpdateCommentResponseDto(CommentEntity commentEntity) {
        this.commentId = commentEntity.getId();
        this.comment = commentEntity.getComment();
        this.modifiedAt = commentEntity.getModifiedAt();
        this.todo = ToDo.of(commentEntity.getToDoEntity());
    }

    @Getter
    private static class ToDo {

        private final Long toDoId;

        private final String title;

        private ToDo(Long toDoId, String title) {
            this.toDoId = toDoId;
            this.title = title;
        }

        private static ToDo of(ToDoEntity toDoEntity) {
            return new ToDo(toDoEntity.getId(), toDoEntity.getTitle());
        }
    }

}
