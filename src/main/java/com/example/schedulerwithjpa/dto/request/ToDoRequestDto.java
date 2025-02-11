package com.example.schedulerwithjpa.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ToDoRequestDto {

    @NotNull(message = "사용자 고유 식별자를 입력하세요.")
    @Range(min = 1, message = "1 이상의 수를 입력하세요.")
    private final Long userId;

    @NotBlank
    private final LocalDate date;

    @NotBlank
    @Size(min = 1, max = 10, message = "제목의 길이는 최소 1 최대 10 입니다.")
    private final String title;

    @Size(max = 200, message = "내용의 길이는 최대 200 입니다.")
    private final String toDo;
}
