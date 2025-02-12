package com.example.schedulerwithjpa.dto.response;

import com.example.schedulerwithjpa.entity.CommentEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CreateCommentResponseDto {

    private final Long id;

    private final String comment;

    private final LocalDateTime createdAt;

    public CreateCommentResponseDto(CommentEntity commentEntity) {
        this.id = commentEntity.getId();
        this.comment = commentEntity.getComment();
        createdAt = commentEntity.getCreatedAt();
    }
}
