package com.example.schedulerwithjpa.dto.response;

import com.example.schedulerwithjpa.entity.CommentEntity;
import com.example.schedulerwithjpa.entity.ToDoEntity;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetCommentsResponseDto {
    private final Long toDoId;

    private final String title;

    private final LocalDate date;

    private final Integer commentNum;

    private final List<Comment> comments;

    public GetCommentsResponseDto(ToDoEntity toDoEntity) {
        this.toDoId = toDoEntity.getId();
        this.title = toDoEntity.getTitle();
        this.date = toDoEntity.getDate();
        this.comments = Comment.ofList(toDoEntity.getCommentEntities());
        this.commentNum = this.comments.size();

    }

    @Getter
    private static class Comment {

        private final Long commentId;

        private final String comment;

        private final LocalDateTime modifiedAt;

        private Comment(Long commentId, String comment, LocalDateTime modifiedAt) {
            this.commentId = commentId;
            this.comment = comment;
            this.modifiedAt = modifiedAt;
        }

        private static List<Comment> ofList(List<CommentEntity> commentEntities) {
            return commentEntities.stream()
                    .map(Comment::of)
                    .toList();
        }

        private static Comment of(CommentEntity commentEntity) {
            return new Comment(commentEntity.getId(), commentEntity.getComment(), commentEntity.getModifiedAt());
        }
    }
}
