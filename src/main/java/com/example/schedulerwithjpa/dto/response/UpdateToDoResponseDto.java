package com.example.schedulerwithjpa.dto.response;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UpdateToDoResponseDto {

    private Long id;

    private Long userId;

    private String username;

    private String title;

    private String content;

    private LocalDate date;

    private LocalDateTime modifiedAt;
}
