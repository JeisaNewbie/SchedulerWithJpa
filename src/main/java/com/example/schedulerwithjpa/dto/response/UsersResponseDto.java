package com.example.schedulerwithjpa.dto.response;

import com.example.schedulerwithjpa.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UsersResponseDto {
    private Long id;

    private String username;

    private String email;

    private LocalDateTime createdAt;

    private Long scheduleNum;

    public UsersResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }
}
