package com.example.schedulerwithjpa.dto.response;

import com.example.schedulerwithjpa.entity.UserEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateUserResponseDto {
    private Long id;

    private String username;

    private String email;

    private LocalDateTime createdAt;

    public CreateUserResponseDto(UserEntity savedUserEntity) {
        this.id = savedUserEntity.getId();
        this.username = savedUserEntity.getUsername();
        this.email = savedUserEntity.getEmail();
        this.createdAt = savedUserEntity.getCreatedAt();
    }
}
