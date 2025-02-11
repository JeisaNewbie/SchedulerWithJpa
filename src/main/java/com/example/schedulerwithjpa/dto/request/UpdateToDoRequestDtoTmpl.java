package com.example.schedulerwithjpa.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UpdateToDoRequestDtoTmpl {

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "이메일 형식을 지켜주세요.")
    private final String email;

    @NotNull(message = "비밀번호를 적어주세요.")
    @Range(min = 999, max = 9999999999L, message = "비밀번호는 최소 1자리 최대 10자리 입니다.")
    private final Long password;

    @NotNull
    private final LocalDate date;

    @NotBlank
    @Size(min = 1, max = 10, message = "제목의 길이는 최소 1 최대 10 입니다.")
    private final String title;

    @Size(max = 200, message = "내용의 길이는 최대 200 입니다.")
    private final String toDo;
}
