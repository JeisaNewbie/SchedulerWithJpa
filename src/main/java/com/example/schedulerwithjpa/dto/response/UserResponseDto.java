package com.example.schedulerwithjpa.dto.response;

import com.example.schedulerwithjpa.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserResponseDto {
    private Long id;

    private String username;

    private String email;

    private LocalDateTime createdAt;

    private List<Schedule> schedules;

    public UserResponseDto(UserEntity savedUserEntity) {
        this.id = savedUserEntity.getId();
        this.username = savedUserEntity.getUsername();
        this.email = savedUserEntity.getEmail();
        this.createdAt = savedUserEntity.getCreatedAt();
//        this.schedules = Schedule.getList(savedUser);
    }

    @Builder
    private static class Schedule {

        private Long scheduleId;

        private String title;

        private String content;

        private LocalDate date;

        public static List<Schedule> getList(UserEntity savedUserEntity) {
            List<Schedule> schedules = new ArrayList<>();
            return null;
        }
    }
}
