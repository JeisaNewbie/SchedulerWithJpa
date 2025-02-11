package com.example.schedulerwithjpa.dto.response;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ToDoResponseDto {
    private Long scheduleId;

    private String username;

    private String title;

    private String content;

    private LocalDate date;

    private LocalDateTime modifiedAt;

    private Long commentNum;

    private List<Comment> comments;

    private static class Comment {

        private Long commentId;

        private String comment;

    }
}
