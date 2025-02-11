package com.example.schedulerwithjpa.dto.response;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ToDoResponseDto {
    private Long toDoId;

    private String username;

    private String title;

    private String content;

    private LocalDate date;

    private LocalDateTime modifiedAt;

    private Long commentNum;

    private List<Comment> comments;

    @Getter
    private static class Comment {

        private final Long commentId;

        private final String comment;

        private Comment(Long commentId, String comment) {
            this.commentId = commentId;
            this.comment = comment;
        }

    }
}
