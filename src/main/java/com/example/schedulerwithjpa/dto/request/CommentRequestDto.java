package com.example.schedulerwithjpa.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
@AllArgsConstructor
public class CommentRequestDto {

    @NotNull(message = "일정 고유 식별자를 입력하세요.")
    @Range(min = 1, message = "1 이상의 수를 입력하세요.")
    private final Long userId;

    @NotNull(message = "일정 고유 식별자를 입력하세요.")
    @Range(min = 1, message = "1 이상의 수를 입력하세요.")
    private final Long toDoId;

    @NotBlank
    @Size(min = 1, max = 50, message = "댓글의 길이는 최소 1 최대 50 입니다.")
    private final String comment;
}
