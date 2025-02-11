package com.example.schedulerwithjpa.dto.response;

import com.example.schedulerwithjpa.entity.UserEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetUsersResponseDto {
    private Long id;

    private String username;

    private String email;

    private LocalDateTime createdAt;

    private Integer toDoNum;

    public GetUsersResponseDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.email = userEntity.getEmail();
        this.createdAt = userEntity.getCreatedAt();
        this.toDoNum = userEntity.getToDoEntities().size();
    }
}
