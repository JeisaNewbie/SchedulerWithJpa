package com.example.schedulerwithjpa.dto.response;

import com.example.schedulerwithjpa.entity.CommentEntity;
import com.example.schedulerwithjpa.entity.ToDoEntity;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetToDoResponseDto {
    private Long toDoId;

    private String username;

    private String title;

    private String toDo;

    private LocalDate date;

    private LocalDateTime modifiedAt;

    private Integer commentNum;

    private List<Comment> comments;

    public GetToDoResponseDto(ToDoEntity toDoEntity) {
        this.toDoId = toDoEntity.getId();
        this.username = toDoEntity.getUserEntity().getUsername();
        this.title = toDoEntity.getTitle();
        this.toDo = toDoEntity.getToDo();
        this.date = toDoEntity.getDate();
        this.modifiedAt = toDoEntity.getModifiedAt();
        this.comments = Comment.ofList(toDoEntity.getCommentEntities());
        this.commentNum = comments.size();
    }

    @Getter
    private static class Comment {

        private final Long commentId;

        private final String comment;

        private Comment(Long commentId, String comment) {
            this.commentId = commentId;
            this.comment = comment;
        }

        private static List<Comment> ofList(List<CommentEntity> commentEntities) {
            return commentEntities.stream()
                    .map(Comment::of)
                    .toList();
        }

        private static Comment of(CommentEntity commentEntity) {
            return new Comment(commentEntity.getId(), commentEntity.getComment());
        }

    }
}
