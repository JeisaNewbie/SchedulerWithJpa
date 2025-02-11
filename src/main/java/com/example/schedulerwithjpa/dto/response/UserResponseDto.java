package com.example.schedulerwithjpa.dto.response;

import com.example.schedulerwithjpa.entity.User;
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

    public UserResponseDto(User savedUser) {
        this.id = savedUser.getId();
        this.username = savedUser.getUsername();
        this.email = savedUser.getEmail();
        this.createdAt = savedUser.getCreatedAt();
//        this.schedules = Schedule.getList(savedUser);
    }

    @Builder
    private static class Schedule {

        private Long scheduleId;

        private String title;

        private String content;

        private LocalDate date;

        public static List<Schedule> getList(User savedUser) {
            List<Schedule> schedules = new ArrayList<>();
            return null;
        }
    }
}
